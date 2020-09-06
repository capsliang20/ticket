package com.line.ticket.mini.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SiriusContainer<T> implements Delayed {
    private volatile boolean finished;

    private final String key;

    private final List<T> elementList;

    private final int threshold;

    private final long deadlineNanos;

    public SiriusContainer(String key, int threshold, long delay, TimeUnit unit) {
        this.key = key;
        this.threshold = threshold;
        this.elementList = new ArrayList<>(threshold);
        this.deadlineNanos = deadlineNanos(unit.toNanos(delay));
    }

    public synchronized boolean add(T[] elements) {
        if (ArrayUtils.isEmpty(elements))
            return true;
        elementList.addAll(Arrays.asList(elements));
        if (elementList.size() < threshold)
            return true;
        log.info("add full. messageId:{}, threshold:{}, size:{}", key, threshold, elementList.size());
        return handle();
    }

    public synchronized boolean handle() {
        if (finished)
            return true;
        log.info("now finished handle. key:{}, threshold:{}, size:{}", key, threshold, elementList.size());
        finished = true;
        return true;
    }

    private long deadlineNanos(long delay) {
        long deadlineNanos = System.nanoTime() + delay;
        return deadlineNanos < 0 ? Long.MAX_VALUE : deadlineNanos;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(Math.max(0L, deadlineNanos - System.nanoTime()), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o) {
            return 0;
        }
        SiriusContainer<?> that = (SiriusContainer<?>) o;
        long d = this.deadlineNanos - that.deadlineNanos;
        return d < 0 ? -1 : 1;
    }

    public String getKey() {
        return key;
    }
}
