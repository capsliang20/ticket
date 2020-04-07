package com.line.ticket.log.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.Iterator;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        receive();
    }

    private static void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress("localhost", 80));
        System.out.println("UDP 服务器启动成功");
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    SocketAddress client = datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(Arrays.toString(buffer.array()));
                    buffer.clear();
                }
            }
            iterator.remove();
        }
        selector.close();
        datagramChannel.close();
    }
}
