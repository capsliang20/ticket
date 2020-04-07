package com.line.ticket.log.netty.other;

public class NIOTimeClient {
    public void main(String[] args) {
        int port = 8080;
        new Thread(new NIOTimeClientHandler("localhost", port), "NIOTimeClient-001")
                .start();
    }
}
