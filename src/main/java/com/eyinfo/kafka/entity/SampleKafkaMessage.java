package com.eyinfo.kafka.entity;

import com.eyinfo.kafka.properties.KafkaMessage;

public class SampleKafkaMessage implements KafkaMessage {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息体
     */
    private String body;

    /**
     * 消费标识
     */
    private String consumerTag;

    public SampleKafkaMessage(String topic, String body, String consumerTag) {
        this.topic = topic;
        this.body = body;
        this.consumerTag = consumerTag;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getConsumerTag() {
        return this.consumerTag;
    }
}
