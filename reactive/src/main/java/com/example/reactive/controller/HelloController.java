package com.example.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/health")
    public Mono<String> getUser(){
        return Mono.just("l am alive");
    }

    @GetMapping("/flux")
    public Flux<String> getFlux(){
        List<String > list = new ArrayList<>();
        for (int a= 0;a < 10;a++) {
            list.add("hello");
        }
        return Flux.fromIterable(list);

    }

}
