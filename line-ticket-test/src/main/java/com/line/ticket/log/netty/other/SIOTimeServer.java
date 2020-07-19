package com.line.ticket.log.netty.other;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SIOTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            SIOTimeServerHandlerExecutePool singleExecutor = new SIOTimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new BIOTimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
