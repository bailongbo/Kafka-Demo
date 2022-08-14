package com.my.demo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author bailb
 * @version 1.0
 * @description
 * @date 2022/8/14 23:08
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public String send(){
        kafkaTemplate.send("test-topic","666");
        return "ok";
    }

}