package com.line.ticket.mini.redisson;

import com.line.ticket.mini.elastic.Record;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://39.108.225.165:6379")
                .setClientName("root")
                .setPassword("hat123");

        RedissonClient client = Redisson.create(config);

        RMap<String, Record> map = client.getMap("testMap");

        Record record = new Record();
        record.setDayno("2222");
        record.setMediaIds("xxxasdas");
        record.setBuuid("213124124");
        record.setStrategy("msmsmsm");
        map.put("no1", record);

        System.out.println(client.getMap("testMap"));
        System.out.println(client.getMapCache("testMap"));

        Paths.get("");
        Path.of("");
    }
}
