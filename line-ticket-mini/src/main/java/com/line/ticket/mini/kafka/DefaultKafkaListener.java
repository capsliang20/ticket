package com.line.ticket.mini.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class DefaultKafkaListener implements MessageListener<String, String> {
    private final CountDownLatch latch = new CountDownLatch(5);


    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(id = "default", topics = "annotated")
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println(data.toString());
        latch.countDown();
    }
}
