package com.eyinfo.kafka.entity;

import org.springframework.kafka.support.Acknowledgment;

import java.io.Serializable;
import java.util.Map;

public class TransferEntity implements Serializable {

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 消费标识,用于区分业务
     */
    private String consumerTag;

    /**
     * Handle for acknowledging the processing
     */
    private Acknowledgment ack;

    /**
     * 消费内容
     */
    private String body;

    /**
     * topic group
     */
    private String groupId;

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 头信息
     */
    private Map<String, Object> headers;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public void setConsumerTag(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    public void setAck(Acknowledgment ack) {
        ack.acknowledge();
        this.ack = ack;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void acknowledge() {
        if (ack != null) {
            ack.acknowledge();
        }
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}
