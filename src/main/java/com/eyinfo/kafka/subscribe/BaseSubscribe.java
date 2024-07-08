package com.eyinfo.kafka.subscribe;

import com.eyinfo.foundation.utils.ObjectJudge;
import com.eyinfo.kafka.KafkaUtils;
import com.eyinfo.kafka.entity.TransferEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Map;

public abstract class BaseSubscribe extends ConsumerRunnable {
    public void onReceiveKafkaMessage(ConsumerRecord<String, String> record,
                                      Acknowledgment ack,
                                      String groupId,
                                      String messageId,
                                      String consumerTag) {
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
        TransferEntity entity = new TransferEntity();
        entity.setAck(ack);
        entity.setBody(record.value());
        entity.setMessageId(messageId);
        entity.setConsumerTag(consumerTag);
        runnable.run(entity);
    }
}
