package com.eyinfo.kafka.entity;

import com.eyinfo.foundation.utils.JsonUtils;
import com.eyinfo.foundation.utils.TextUtils;
import com.eyinfo.kafka.properties.KafkaMessage;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BodyMessage implements Message<String> {

    private KafkaMessage msg;
    private String groupId;
    private String messageId;
    private MessageHeaders messageHeaders;

    public BodyMessage(KafkaMessage msg, String groupId) {
        this.msg = msg;
        this.groupId = groupId;
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.GROUP_ID, groupId);
        headers.put(KafkaHeaders.TOPIC, msg.getTopic());
        messageHeaders = new MessageHeaders(headers);
    }

    @Override
    public String getPayload() {
        TransferBody transferBody = new TransferBody();
        transferBody.setGroupId(groupId);
        transferBody.setMessageId(getMessageId());
        transferBody.setConsumerTag(msg.getConsumerTag());
        transferBody.setBody(msg.getBody());
        return JsonUtils.toStr(transferBody);
    }

    public String getMessageId() {
        if (!TextUtils.isEmpty(messageId)) {
            return messageId;
        }
        UUID uuid = messageHeaders.getId();
        messageId = uuid.toString();
        return messageId;
    }

    @Override
    public MessageHeaders getHeaders() {
        return messageHeaders;
    }
}
