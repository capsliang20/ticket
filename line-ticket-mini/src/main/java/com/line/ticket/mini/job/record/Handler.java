package com.line.ticket.mini.job.record;

import com.line.ticket.mini.job.record.RecordProducer.Signal;
import com.line.ticket.mini.model.shard.Record;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Handler {

    public static void handle(String dirPath) throws Exception {
        ArrayBlockingQueue<Record> dataNodeQueue = new ArrayBlockingQueue<>(64);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Path[] paths = Files.list(FileSystems.getDefault().getPath(dirPath)).toArray(Path[]::new);
        RecordProducer recordProducer = new RecordProducer(paths, dataNodeQueue);
        Future<Integer> producerFuture = executorService.submit(recordProducer);
        Signal signal = recordProducer.getSignal();
        ArrayDeque<Future<Integer>> consumerQueue = new ArrayDeque<>();
        for (int i = 0; i < 15; i++)
            consumerQueue.offer(executorService.submit(new RecordConsumer(dataNodeQueue, signal)));
        System.out.println("Handle finished.");
        System.out.println("producer build " + producerFuture.get() + " dataNodes.");
        Future<Integer> consumerFuture;
        for (int i = 0; i < 15; i++) {
            consumerFuture = consumerQueue.poll();
            System.out.println("consumer_" + i + " has consumed " + consumerFuture.get() + " dataNodes.");
        }
        System.out.println(executorService.isTerminated());
    }


}
