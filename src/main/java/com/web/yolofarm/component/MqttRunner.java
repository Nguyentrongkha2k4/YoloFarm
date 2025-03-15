package com.web.yolofarm.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.web.yolofarm.service.MqttService;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MqttRunner implements CommandLineRunner {
    private final MqttService mqttService;
    private final String[] feeds={"bbc-led", "bbc-temp"};
    @Override
    public void run(String... args) throws Exception {
        for (String feed : feeds) {
            mqttService.startListening(feed);
        }
    }

}
