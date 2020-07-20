package com.line.ticket.log.nio;

import java.nio.IntBuffer;

public class UseBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(20);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();

        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            System.out.println("j = " + j);
        }

        System.out.println("position = " + intBuffer.position());
        System.out.println("limit = " + intBuffer.limit());

        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            System.out.println("j = " + j);
        }

        System.out.println("position = " + intBuffer.position());
        System.out.println("limit = " + intBuffer.limit());

        intBuffer.compact();
        System.out.println("position = " + intBuffer.position());
        System.out.println("limit = " + intBuffer.limit());

    }
}
