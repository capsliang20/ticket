package com.line.ticket.log.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

public class EchoServerReactor implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 80));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    dispatch(key);
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class EchoHandler implements Runnable {
        private final SocketChannel channel;
        private final SelectionKey selectionKey;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        private static final int RECEIVING = 0, SENDING = 1;
        private int state = RECEIVING;

        EchoHandler(Selector selector, SocketChannel channel) throws IOException {
            this.channel = channel;
            channel.configureBlocking(false);
            this.selectionKey = channel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(this);
            selector.wakeup();
        }

        @Override
        public void run() {
            try {
                if (state == SENDING) {
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                } else if (state == RECEIVING) {
                    int length = 0;
                    while ((length = channel.read(byteBuffer)) > 0) {
                        System.out.println("read length: " + length + ", byteBuffer: " + Arrays.toString(byteBuffer.array()));
                    }
                    byteBuffer.flip();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
