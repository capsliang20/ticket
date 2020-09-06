package com.line.ticket.mini.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

//单向传输用于中可靠性情况
public class OneWayProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("one-way-producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("one-way-topic", "TagA",
                    ("Hello RocketMQ, i am one way No." + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
        }
        Thread.sleep(5000);
        producer.shutdown();
    }
}
