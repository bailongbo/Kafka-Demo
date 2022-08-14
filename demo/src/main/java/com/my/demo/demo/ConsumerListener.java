package com.my.demo.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author bailb
 * @version 1.0
 * @description
 * @date 2022/8/14 23:10
 */
@Component
public class ConsumerListener {
    @KafkaListener(topics = "test-topic",groupId = "test-group")
    public void onMessage(String msg){
        System.out.println("----收到消息："+msg+"----");
    }
}
