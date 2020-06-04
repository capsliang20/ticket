package com.line.ticket.mini.config.shard;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SnowflakeShardingKeyGenerator generator = new SnowflakeShardingKeyGenerator();
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        int[] bucket={0,0,0,0,0,0,0,0};
        for (int i = 0; i < 100; i++) {
            Long num = (Long) snowflakeShardingKeyGenerator.generateKey();
            int calNum = num.toString().hashCode();
            int tb = (int) (calNum & 7);
            bucket[tb]++;
            System.out.println("calNum: " + calNum + "binary: " + Long.toBinaryString(calNum) + ", tb: " + tb);
        }
        System.out.println(Arrays.toString(bucket));
    }
}
