package com.eyinfo.kafka.subscribe;

import com.eyinfo.foundation.utils.ObjectJudge;
import com.eyinfo.kafka.KafkaConstanst;
import com.eyinfo.kafka.KafkaUtils;
import com.eyinfo.kafka.entity.TransferEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Map;

public abstract class BaseSubscribe<T> extends ConsumerRunnable<T> {
    public void onReceiveKafkaMessage(ConsumerRecord<String, String> record, Acknowledgment ack,
                                      @Header(KafkaHeaders.GROUP_ID) String groupId,
                                      @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageId,
                                      @Header(KafkaConstanst.consumerTag) String consumerTag) {
        Map<String, ConsumerRunnable> consumerMap = KafkaUtils.getConsumerMap();
        if (ObjectJudge.isNullOrEmpty(consumerMap)) {
            return;
        }
        String topic = record.topic();
        String key = String.format("%s_%s", groupId, topic);
        ConsumerRunnable runnable = consumerMap.get(key);
        if (runnable == null) {
            return;
        }
        TransferEntity<Object> entity = new TransferEntity<>();
        entity.setAck(ack);
        entity.setBody(record.value());
        entity.setMessageId(messageId);
        entity.setConsumerTag(consumerTag);
        runnable.run(entity);
    }
}
