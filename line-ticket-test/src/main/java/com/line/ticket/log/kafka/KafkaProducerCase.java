package com.line.ticket.log.kafka;

import com.line.ticket.log.avro.clazz.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerCase {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer(kafkaProps);
        for (int i = 0; i < 5; i++) {
            User user = new User("user_" + i, i + 5, "color_" + i);
            ProducerRecord<String, String> record = new ProducerRecord<>(MQConstant.CASE_TOPIC, "key_" + i, "Now it is value " + i);
            RecordMetadata result = producer.send(record).get();
            System.out.println("record: " + i);
            System.out.println("result.topic " + result.topic());
            System.out.println("result.partition " + result.partition());
            System.out.println("result.offset " + result.offset());
            System.out.println("result.timestamp " + result.timestamp());
            Thread.sleep(1000);
        }
    }
}
