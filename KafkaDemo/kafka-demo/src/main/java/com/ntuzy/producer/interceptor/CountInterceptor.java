package com.ntuzy.producer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author IamZY
 * @create 2020/5/30 9:56
 */
public class CountInterceptor implements ProducerInterceptor<String, String> {

    private int success;
    private int error;

    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            success++;
        } else {
            error++;
        }
    }

    public void close() {
        System.out.println("success: " + success);
        System.out.println("error: " + error);
    }

    public void configure(Map<String, ?> configs) {

    }
}
