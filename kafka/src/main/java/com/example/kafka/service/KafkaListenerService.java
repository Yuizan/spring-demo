package com.example.kafka.service;

public interface KafkaListenerService {

    void onMessage(String message);

}
