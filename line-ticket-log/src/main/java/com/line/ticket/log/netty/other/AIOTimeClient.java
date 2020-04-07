package com.line.ticket.log.netty.other;

public class AIOTimeClient {
    public static void main(String[] args){
        int port=8080;
        new Thread(new AIOTImeClientHandler("localhost",port),"AIO-TimeClientClient-001").start();
    }
}
