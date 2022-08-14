package com.my.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author bailb
 * @version 1.0
 * @description 、
 * @date 2022/8/14 22:42
 */
public class Produce {

    public static void main(String[] args) {
        Properties props=new Properties();
        props.put("bootstrap.servers","192.168.16.129:9092");

        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        props.put("acks","1");
        props.put("retries",3);
        props.put("batch.size",16384);
        props.put("linger.ms",5);
        props.put("buffer.memory",33554432);
        props.put("max.block.ms",3000);

        Producer<String,String> producer = new KafkaProducer<String,String>(props);

        for (int i =0 ;i<100;i++) {
            producer.send(new ProducerRecord<String,String>("commit-test",Integer.toString(i),Integer.toString(i)));
        }


        producer.close();

    }

}
