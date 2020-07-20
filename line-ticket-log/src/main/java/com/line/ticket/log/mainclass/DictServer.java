package com.line.ticket.log.mainclass;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class DictServer {

    public static final int PORT=20220;

    public static void main(String[] args) throws IOException {
        try(ServerSocket server=new ServerSocket(PORT)){
            while (true){
                try(Socket connection=server.accept()) {
                    Writer out=new OutputStreamWriter(connection.getOutputStream());
                    LocalDateTime now = LocalDateTime.now();
                    out.write(now+"\r\n");
                    System.out.println(now+"\r\n");
                    out.flush();
                }
            }
        }
    }
}
