package com.eyinfo.kafka.subscribe;

import com.eyinfo.kafka.entity.TransferEntity;

public abstract class ConsumerRunnable<T> {
    public ConsumerRunnable() {

    }

    /**
     * kafka消费回调
     *
     * @param entity 数据传输对象
     */
    public abstract void run(TransferEntity<T> entity);
}
