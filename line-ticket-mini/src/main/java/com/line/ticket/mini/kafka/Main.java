package com.line.ticket.mini.kafka;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<SiriusContainer<String>> queue = new DelayQueue<>();
        //msgId: about_one 容量10 延迟15 容量触发
        SiriusContainer<String> aboutOne = new SiriusContainer<>("about_one", 10, 5, TimeUnit.SECONDS);
        //msgId: about_two 容量10 延迟20 延迟触发
        SiriusContainer<String> aboutTwo = new SiriusContainer<>("about_two", 10, 10, TimeUnit.SECONDS);

        queue.add(aboutOne);
        queue.add(aboutTwo);

        Thread.sleep(3000);
        System.out.println("one: " + queue.poll() + ", aboutOne.getDelay(): " + aboutOne.getDelay(TimeUnit.SECONDS) + ", aboutTwo.getDelay(): " + aboutTwo.getDelay(TimeUnit.SECONDS));
        Thread.sleep(3000);
        System.out.println("two: " + queue.poll()+ ", aboutOne.getDelay(): " + aboutOne.getDelay(TimeUnit.SECONDS) + ", aboutTwo.getDelay(): " + aboutTwo.getDelay(TimeUnit.SECONDS));
        Thread.sleep(3000);
        System.out.println("three: " + queue.poll() + ", aboutOne.getDelay(): " + aboutOne.getDelay(TimeUnit.SECONDS) + ", aboutTwo.getDelay(): " + aboutTwo.getDelay(TimeUnit.SECONDS));
        Thread.sleep(3000);
        System.out.println("four: " + queue.poll()+ ", aboutOne.getDelay(): " + aboutOne.getDelay(TimeUnit.SECONDS) + ", aboutTwo.getDelay(): " + aboutTwo.getDelay(TimeUnit.SECONDS));
        Thread.sleep(3000);
        System.out.println("five: " + queue.poll()+ ", aboutOne.getDelay(): " + aboutOne.getDelay(TimeUnit.SECONDS) + ", aboutTwo.getDelay(): " + aboutTwo.getDelay(TimeUnit.SECONDS));

    }
}
