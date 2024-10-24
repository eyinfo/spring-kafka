package com.eyinfo.kafka.listener;

import java.util.Map;

public interface KafkaHeaderBindListener {
    void onHeaders(Map<String, Object> headers);
}
