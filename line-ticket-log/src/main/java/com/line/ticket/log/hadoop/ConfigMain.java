package com.line.ticket.log.hadoop;

import org.apache.hadoop.conf.Configuration;

public class ConfigMain {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("hadoop/configuration-1.xml");
        System.out.println(conf.get("color"));
        System.out.println(conf.get("weight"));
        System.out.println(conf.get("size"));
        System.out.println(conf.get("size-weight"));
    }
}
