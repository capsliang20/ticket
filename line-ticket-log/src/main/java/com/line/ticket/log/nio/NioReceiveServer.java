package com.line.ticket.log.nio;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioReceiveServer {
    public static void main(String[] args) throws IOException {
        NioReceiveServer server = new NioReceiveServer();
        server.startServer();
    }

    static class Client {
        String fileName;
        long fileLength;
        long startTime;
        InetSocketAddress remoteAddress;
        FileChannel outChannel;
    }

    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    Map<SelectableChannel, Client> clientMap = new HashMap<>();

    public void startServer() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();


        serverChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("localhost", 80);

        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.bind(address);

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("serverChannel is listening ... ");
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    System.out.println(socketChannel.getRemoteAddress() + "连接成功");
                } else if (key.isReadable()) {
                    processData(key);
                }
                iterator.remove();
            }
        }
    }

    private void processData(SelectionKey key) {
        Client client = clientMap.get(key.channel());
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int num = 0;
        try {
            buffer.clear();
            while ((num = socketChannel.read(buffer)) > 0) {
                if (null == client.fileName) {
                    String fileName = Charsets.UTF_8.decode(buffer).toString();
                    String destPath = "";
                    File directory = new File(destPath);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    client.fileName = fileName;
                    String fullName = directory.getAbsolutePath() + File.separator + fileName;
                    System.out.println("NIO 传输目标文件: " + fullName);
                    File file = new File(fullName);
                    FileChannel fileChannel = new FileOutputStream(file).getChannel();
                    client.outChannel = fileChannel;
                } else if (0 == client.fileLength) {
                    long fileLength = buffer.getLong();
                    client.fileLength = fileLength;
                    client.startTime = System.currentTimeMillis();
                    System.out.println("NIO 传输开始: ");
                } else {
                    client.outChannel.write(buffer);
                }
                buffer.clear();
            }
            key.cancel();
        } catch (IOException e) {
            key.cancel();
            e.printStackTrace();
            return;
        }
        if (num == -1) {
            IOUtils.closeQuietly(client.outChannel);
            System.out.println("上传完毕");
            key.cancel();
        }
    }
}
