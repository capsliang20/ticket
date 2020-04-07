package com.line.ticket.log.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        send();
    }
    private static void send() throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("UDP 客户端启动成功!");
        System.out.println("请输入发送内容");
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put((LocalDateTime.now() + " >> " + next).getBytes());
            buffer.flip();
            dChannel.send(buffer, new InetSocketAddress("localhost", 80));
            buffer.clear();
        }
        dChannel.close();
    }
}
