package com.eyinfo.kafka;

import com.eyinfo.foundation.events.Action;
import com.eyinfo.kafka.entity.BodyMessage;
import com.eyinfo.kafka.properties.KafkaMessage;
import com.eyinfo.kafka.subscribe.ConsumerRunnable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;

public class KafkaUtils {
    private static Map<String, ConsumerRunnable> consumerMap = new HashMap<>();

    public static Map<String, ConsumerRunnable> getConsumerMap() {
        return consumerMap;
    }

    public static <T extends ConsumerRunnable> void subscribe(String groupId, String topic, T runnable) {
        String key = String.format("%s_%s", groupId, topic);
        consumerMap.put(key, runnable);
    }

    /**
     * 发送kafka消息
     *
     * @param kafkaTemplate   kafka消息模板
     * @param groupId         消息组id
     * @param message         kafka消息
     * @param successCallback 发送成功回调
     * @param failureCallback 发送失败回调
     */
    public static void send(KafkaTemplate<String, String> kafkaTemplate, String groupId, KafkaMessage message, Action<SendResult<String, String>> successCallback, Action<String> failureCallback) {
        if (kafkaTemplate == null || message == null || message.getBody() == null || message.getTopic() == null || message.getTopic().isEmpty()) {
            return;
        }
        BodyMessage bodyMessage = new BodyMessage(message, groupId);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(bodyMessage);
        listenableFuture.addCallback(success -> {
            if (successCallback != null) {
                successCallback.call(success);
            }
        }, failure -> {
            if (failureCallback != null) {
                failureCallback.call(failure.getMessage());
            }
        });
    }

    /**
     * 发送kafka消息
     *
     * @param kafkaTemplate   kafka消息模板
     * @param groupId         消息组id
     * @param message         kafka消息
     * @param successCallback 发送成功回调
     */
    public static void send(KafkaTemplate<String, String> kafkaTemplate, String groupId, KafkaMessage message, Action<SendResult<String, String>> successCallback) {
        send(kafkaTemplate, groupId, message, successCallback, null);
    }

    /**
     * 发送kafka消息
     *
     * @param kafkaTemplate kafka消息模板
     * @param groupId       消息组id
     * @param message       kafka消息
     */
    public static void send(KafkaTemplate<String, String> kafkaTemplate, String groupId, KafkaMessage message) {
        send(kafkaTemplate, groupId, message, null, null);
    }
}
