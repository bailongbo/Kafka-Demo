package com.my.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author bailb
 * @version 1.0
 * @description
 * @date 2022/8/14 22:41
 */
public class Consumer {
    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.16.129:9092");
        properties.put("group.id","test-group");
        properties.put("enable.auto.commit","false");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("commit-test"));

        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d ,key =%s, value= %s, partition= %s%n" ,record.offset(),record.key(),record.value(),record.partition());
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                // 同步提交
                consumer.commitSync();
                buffer.clear();
            }
        }
    }
}
