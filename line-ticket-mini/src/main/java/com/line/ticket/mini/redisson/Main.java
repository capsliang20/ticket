package com.line.ticket.mini.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://39.108.225.165:6379")
                .setClientName("root")
                .setPassword("hat123");

        RedissonClient client = Redisson.create(config);

        System.out.println(client.getMapCache("testMap"));
    }
}
