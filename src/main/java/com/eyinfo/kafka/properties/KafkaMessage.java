package com.eyinfo.kafka.properties;

import java.util.Map;

public interface KafkaMessage {

    /**
     * 获取消息主题
     *
     * @return kafka topic
     */
    String getTopic();

    /**
     * 获取消息体
     *
     * @return kafka消息内容
     */
    String getBody();

    /**
     * 获取消费标识
     *
     * @return kafka消费标识
     */
    String getConsumerTag();

    /**
     * 获取头信息
     */
    Map<String, Object> getHeaders();

    void setHeaders(Map<String, Object> headers);
}
