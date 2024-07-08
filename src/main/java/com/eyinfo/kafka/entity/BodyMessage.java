package com.eyinfo.kafka.entity;

import com.eyinfo.foundation.utils.JsonUtils;
import com.eyinfo.kafka.KafkaConstanst;
import com.eyinfo.kafka.properties.KafkaMessage;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

public class BodyMessage implements Message<String> {

    private KafkaMessage msg;
    private String groupId;

    public BodyMessage(KafkaMessage msg, String groupId) {
        this.msg = msg;
        this.groupId = groupId;
    }

    @Override
    public String getPayload() {
        String content = "";
        Object body = msg.getBody();
        if (body instanceof String) {
            content = String.valueOf(body);
        } else {
            content = JsonUtils.toStr(body);
        }
        return content;
    }

    @Override
    public MessageHeaders getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.GROUP_ID, groupId);
        headers.put(KafkaConstanst.consumerTag, msg.getConsumerTag());
        headers.put(KafkaHeaders.TOPIC, msg.getTopic());
        return new MessageHeaders(headers);
    }
}
