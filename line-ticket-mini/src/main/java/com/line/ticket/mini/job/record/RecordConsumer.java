package com.line.ticket.mini.job.record;

import com.line.ticket.mini.job.record.RecordProducer.Signal;
import com.line.ticket.mini.mapper.shard.ShardRecordMapper;
import com.line.ticket.mini.model.shard.Record;
import com.line.ticket.mini.util.SpringBeanTool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RecordConsumer implements Callable<Integer> {

    private final ArrayBlockingQueue<Record> recordQueue;

    private final Signal signal;

    RecordConsumer(ArrayBlockingQueue<Record> recordQueue, Signal signal) {
        this.recordQueue = recordQueue;
        this.signal = signal;
    }


    @Override
    public Integer call() throws InterruptedException {
        System.out.println("Now consumer thread: " + Thread.currentThread().toString() + " start work.");
        int count = 0;
        Record record;
        ShardRecordMapper shardRecordMapper = SpringBeanTool.getBean(ShardRecordMapper.class);
        while (((record = recordQueue.poll(5, TimeUnit.SECONDS)) != null) || signal.mark) {
            try {
                shardRecordMapper.coverRecord(record);
                System.out.println("consumer: " + Thread.currentThread().toString() + " consumed " + record.getId() + " to database: " + ((int) (record.getId() >>> 16) & 3) + " table: " + (record.getId().hashCode() & 7));
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
        System.out.println("consumer: " + Thread.currentThread().toString() + " now finished consumed " + count + " records.");
        return count;
    }
}
