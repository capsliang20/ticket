package com.line.ticket.mini.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SiriusKafkaListener implements MessageListener<String, String> {

    private final DelayQueue<SiriusContainer<String>> queue = new DelayQueue<>();

    private final Map<String, SiriusContainer<String>> msgMap = new HashMap<>();

    @Override
    @KafkaListener(id = "siriusListener", topics = "sirius")
    public void onMessage(ConsumerRecord<String, String> data) {
        String messageId = data.key();
        String imeiStr = data.value();
        log.info("now listening the record. messageId:{}, imeiStr:{}", messageId, imeiStr);
        SiriusContainer<String> container = msgMap.get(messageId);
        if (container == null) {
            log.info("first meet messageId:{}", messageId);
            container = new SiriusContainer<>(messageId, 10, 30, TimeUnit.SECONDS);
            msgMap.put(messageId, container);
            queue.offer(container);
        }
        log.info("add:{}", container.add(imeiStr.split(",")));
    }

    public void scan() {
        log.info("now listener start scanning ~~~~~~");
        SiriusContainer<String> container = queue.poll();
        if (container == null)
            return;
        log.info("now handling the container. messageId:{}", container.getKey());
        boolean handleResult = container.handle();
        log.info("handleResult:{}", handleResult);
        msgMap.remove(container.getKey());
    }
}
