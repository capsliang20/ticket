package com.line.ticket.mini.config.test;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        SnowflakeShardingKeyGenerator generator = new SnowflakeShardingKeyGenerator();
//        for (int i = 0; i < 10; i++) {
//            Long key = (Long) generator.generateKey();
//            System.out.println(key);
//            System.out.println(Long.toBinaryString(key));
//            System.out.println(key.intValue());
//            System.out.println(Integer.toBinaryString(key.intValue()));
//            System.out.println(key >>> 22);
//            System.out.println(Integer.toBinaryString((int) (key >>> 22)));
//            System.out.println();
//        }
//        Long id = 100023606246981888l;
//        System.out.println(Long.toBinaryString(id));
//        System.out.println(Integer.toBinaryString(id.intValue()));
//        System.out.println(Integer.toBinaryString((int)(id>>>16)));
//        System.out.println((id.intValue()>>>16) & 3);
//        System.out.println(id.toString().hashCode());
//        System.out.println(id.toString().hashCode() & 7);
        List<String> strings = List.of("222", "2145", "214");
        System.out.println(strings);
        String[] strings1 = new String[]{"222", "2145", "214"};
        System.out.println(Arrays.toString(strings1));
        System.out.println(strings1);
    }
}
