package com.example.kafka.controller;

import com.example.kafka.model.User;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private KafkaTemplate<String,Object> kafkaTemplate;
    private Gson gson;
    public HelloController(KafkaTemplate<String,Object> kafkaTemplate, Gson gson){
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }
    @RequestMapping("/health")
    public String health(){
        return "health";
    }

    @RequestMapping("/send")
    public String send(String message){
        User user = new User("peter", "11");
        kafkaTemplate.send("topic",gson.toJson(user));
        return "success";
    }

}
