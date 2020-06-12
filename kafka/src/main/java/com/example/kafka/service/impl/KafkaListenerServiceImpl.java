package com.example.kafka.service.impl;

import com.example.kafka.service.KafkaListenerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {

    @KafkaListener(topics = "topic")
    public void onMessage(String message){
        System.out.println(message);
    }


}
