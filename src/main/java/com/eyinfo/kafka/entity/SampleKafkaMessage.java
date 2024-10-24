package com.eyinfo.kafka.entity;

import com.eyinfo.kafka.properties.KafkaMessage;

import java.util.Map;

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

    /**
     * 头信息
     */
    private Map<String, Object> headers;

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

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}
