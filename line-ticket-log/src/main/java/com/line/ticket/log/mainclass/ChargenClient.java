package com.line.ticket.log.mainclass;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
    public static int DEFAULT_PORT=19;

    public static void main(String[] args){
        try {
            SocketAddress address=new InetSocketAddress("localhost",DEFAULT_PORT);
            SocketChannel client=SocketChannel.open();
            ByteBuffer buffer=ByteBuffer.allocate(74);
            WritableByteChannel out= Channels.newChannel(System.out);
            while (client.read(buffer)!=-1){
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
