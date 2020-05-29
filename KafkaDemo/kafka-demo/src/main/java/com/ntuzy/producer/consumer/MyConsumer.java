package com.ntuzy.producer.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Author IamZY
 * @create 2020/5/29 16:45
 */
public class MyConsumer {
    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.52.100:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "bigdata");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer consumer = new KafkaConsumer(properties);

        // 订阅主题
        consumer.subscribe(Arrays.asList("first","se"));

        // 获取数据
        while (true) {
            ConsumerRecords<String,String> consumerRecords = consumer.poll(100);

            // 解析并打印ConsumerRecords
            for(ConsumerRecord<String,String> consumerRecord :consumerRecords) {
                System.out.println(consumerRecord.key() + "---" + consumerRecord.value());
            }
        }

    }
}
