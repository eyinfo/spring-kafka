package com.eyinfo.kafka.entity;

import org.springframework.kafka.support.Acknowledgment;

import java.io.Serializable;

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

    public Acknowledgment getAck() {
        return ack;
    }

    public void setAck(Acknowledgment ack) {
        this.ack = ack;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
