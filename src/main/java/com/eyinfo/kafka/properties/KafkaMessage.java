package com.eyinfo.kafka.properties;

public interface KafkaMessage<T> {

    /**
     * 获取消息主题
     *
     * @return kafka topic
     */
    public String getTopic();

    /**
     * 获取消息体
     *
     * @return kafka消息内容
     */
    public T getBody();

    /**
     * 获取消费标识
     *
     * @return kafka消费标识
     */
    public String getConsumerTag();
}
