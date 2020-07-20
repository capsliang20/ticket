package com.line.ticket.log.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerCase {
    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProps);
        consumer.subscribe(Arrays.asList(MQConstant.CASE_TOPIC));
        System.out.println(consumer.subscription().toString());
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            System.out.println(records.isEmpty());
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("result.topic " + record.topic());
                System.out.println("result.key " + record.key());
                System.out.println("result.value " + record.value());
                System.out.println("result.partition " + record.partition());
                System.out.println("result.offset " + record.offset());
                System.out.println("result.timestamp " + record.timestamp());
            }
        }

    }
}
