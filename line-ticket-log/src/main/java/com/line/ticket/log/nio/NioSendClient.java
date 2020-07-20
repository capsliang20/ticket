package com.line.ticket.log.nio;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioSendClient {
    private Charset charset = Charsets.UTF_8;

    public void sendFile() {
        try {
            File file = new File(ResourceConstant.SOURCE_PATH);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(InetSocketAddress.createUnresolved("localhost", 80));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()) {

            }
            System.out.println("成功连接到服务器");
            ByteBuffer fileNameByteBuffer = charset.encode(ResourceConstant.DEST_PATH);
            socketChannel.write(fileNameByteBuffer);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.putLong(file.length());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            System.out.println("开始传输文件");
            int length = 0;
            long process = 0;
            while ((length = fileChannel.read(buffer)) > 0) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                process += length;
                System.out.println("|" + (100 * process / file.length()) + "% |");
            }

            if (length == -1) {
                IOUtils.closeQuietly(fileChannel);
                socketChannel.shutdownOutput();
                IOUtils.closeQuietly(socketChannel);
            }
            System.out.println("传输文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
