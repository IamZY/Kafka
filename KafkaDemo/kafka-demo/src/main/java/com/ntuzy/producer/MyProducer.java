package com.ntuzy.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author IamZY
 * @create 2020/5/28 16:49
 */
public class MyProducer {


    public static void main(String[] args) {

        // 创建kafka生产者的配置信息
        Properties properties = new Properties();

        // 指定连接的kafka集群
        // kafka server.properties 中的 adviceor.listen=PLAINTEXT://当前机器ip:9092
        properties.put("bootstrap.servers", "192.168.52.100:9092");

        // ack的应答级别
        properties.put("acks", "all");

        // 重试次数
        properties.put("retries", 1);

        // 批次大小
        properties.put("batch.size", 16384);

        // 等待时间
        properties.put("linger.ms", 1);


        //RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);

        // key value的序列化类
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 发送数据
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("first", "ntuzy--" + i));
        }
        producer.close();

    }
}