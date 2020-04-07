package com.line.ticket.log.netty.other;

public class AIOTimeServer {
    public static void main(String[] args){
        int port=8080;
        AIOTimeServerHandler timeServer = new AIOTimeServerHandler(port);
        new Thread(timeServer,"AIO-TimeServerHandler-001").start();
    }
}
