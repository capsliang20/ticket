package com.line.ticket.log.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

public class NioDiscardServer {
    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",80));
        System.out.println("服务器启动成功");
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketCahnnel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer= ByteBuffer.allocate(1024);
                    int length=0;
                    while ((length=socketCahnnel.read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(Arrays.toString(byteBuffer.array()));
                        byteBuffer.clear();
                    }
                    socketCahnnel.close();
                }
                iterator.remove();
            }
        }
        serverSocketChannel.close();
    }
}
