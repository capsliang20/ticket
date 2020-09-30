package com.line.ticket.mini.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class KafkaMain {

    public static void main(String[] args) throws Exception {
        KafkaMain kafkaMain=new KafkaMain();
        kafkaMain.test();
    }

    public void test() throws Exception {
        System.out.println("start auto test");
        ContainerProperties containerProperties = new ContainerProperties("topic1", "topic2");
        final CountDownLatch latch = new CountDownLatch(15);
        containerProperties.setMessageListener((MessageListener<Integer, String>) data -> {
            System.out.println("received: " + data.toString());
            latch.countDown();
        });

        KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProperties);
        container.setBeanName("testAuto");
        container.start();
        Thread.sleep(1000);
        KafkaTemplate<Integer, String> template = createTemplate();
        template.setDefaultTopic("topic1");
        template.sendDefault(0, "foo");
        template.sendDefault(1, "bar");
        template.sendDefault(2, "baz");
        template.sendDefault(1, "qux");
        template.flush();
        latch.await();
        System.out.println("Now will stop the container");
        container.stop();
        System.out.println("finished!");
    }

    private KafkaMessageListenerContainer<Integer, String> createContainer(ContainerProperties containerProperties) {
        Map<String, Object> props = consumerProps();
        ConsumerFactory<Integer, String> consumerFactory = new DefaultKafkaConsumerFactory<>(props);
        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    private KafkaTemplate<Integer, String> createTemplate() {
        Map<String, Object> props = senderProps();
        ProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        return new KafkaTemplate<>(producerFactory);
    }

    private Map<String, Object> consumerProps() {
        return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094",
                ConsumerConfig.GROUP_ID_CONFIG, "demo-group",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true,
                ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100",
                ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    }

    private Map<String, Object> senderProps() {
        return Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094",
                ProducerConfig.RETRIES_CONFIG, 0,
                ProducerConfig.BATCH_SIZE_CONFIG, 16384,
                ProducerConfig.LINGER_MS_CONFIG, 1,
                ProducerConfig.BUFFER_MEMORY_CONFIG, 335544,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }
}
