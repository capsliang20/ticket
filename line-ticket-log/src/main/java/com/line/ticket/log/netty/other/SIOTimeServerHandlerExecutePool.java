package com.line.ticket.log.netty.other;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SIOTimeServerHandlerExecutePool {
    private ExecutorService executor;

    public SIOTimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
        executor=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task){
        executor.execute(task);
    }
}
