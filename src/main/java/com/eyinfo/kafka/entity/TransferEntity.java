package com.eyinfo.kafka.entity;

import org.springframework.kafka.support.Acknowledgment;

import java.io.Serializable;

public class TransferEntity<T> implements Serializable {

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
    private T body;

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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
