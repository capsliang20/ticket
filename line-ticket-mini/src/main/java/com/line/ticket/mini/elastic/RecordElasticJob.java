package com.line.ticket.mini.elastic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.time.LocalDateTime;

public class RecordElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {

//        try {
//            Handler.handle(shardingContext.getShardingItem(),shardingContext.getShardingTotalCount());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        while (true){
            System.out.println(LocalDateTime.now() + " Sharding total count : " + shardingContext.getShardingTotalCount()
                    + ", now it is " + shardingContext.getShardingItem());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
