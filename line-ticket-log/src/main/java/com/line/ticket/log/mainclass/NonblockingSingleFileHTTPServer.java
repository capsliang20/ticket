package com.line.ticket.log.mainclass;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class NonblockingSingleFileHTTPServer {
    private ByteBuffer contentBuffer;
    private int port = 80;

    public NonblockingSingleFileHTTPServer(ByteBuffer data, String encoding, String MIMEType, int port) {
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n"
                + "Server:NonblockingSingleFileHTTPServer\r\n"
                + "Content-length: " + data.limit() + "\r\n"
                + "Content-type: " + MIMEType + "\r\n\r\n";
        byte[] headerData = header.getBytes(Charset.forName("US-ASCII"));
        ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
        buffer.put(headerData);
        buffer.put(data);
        buffer.flip();
        this.contentBuffer = buffer;
    }

    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        InetSocketAddress localPort = new InetSocketAddress(port);
        serverSocket.bind(localPort);
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (buffer.hasRemaining()) {
                            channel.write(buffer);
                        } else {
                            channel.close();
                        }
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        channel.read(buffer);
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            Path file = FileSystems.getDefault().getPath(args[0]);
            byte[] data = Files.readAllBytes(file);
            ByteBuffer input = ByteBuffer.wrap(data);
            int port = 80;
            String encoding = "UTF-8";
            NonblockingSingleFileHTTPServer server = new NonblockingSingleFileHTTPServer(input, encoding, contentType, port);
            server.run();
            ;
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
