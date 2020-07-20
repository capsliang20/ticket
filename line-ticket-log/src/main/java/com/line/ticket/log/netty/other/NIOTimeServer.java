package com.line.ticket.log.netty.other;

public class NIOTimeServer {
    public static void main(String[] args) {
        int port=8080;
        NIOMultiplexerTimeServer timeServer=new NIOMultiplexerTimeServer(port);

    }
}
