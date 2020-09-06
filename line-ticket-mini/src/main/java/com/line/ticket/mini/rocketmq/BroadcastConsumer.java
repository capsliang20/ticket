package com.line.ticket.mini.rocketmq;

import com.line.ticket.mini.util.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class BroadcastConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("broadcast-consumer");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("broadcast-topic", "TagA || TagB || TagC");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println("broadcast-consumer received msg: 【" + JSON.toJSONString(msgs) + "】");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("broadcast-consumer started.");
    }
}
