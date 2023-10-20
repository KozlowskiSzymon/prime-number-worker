package com.example.primenumberworker.control;

import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.Queue;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Queue<String> stringQueue() {
        return new LinkedList<>();
    }
}
