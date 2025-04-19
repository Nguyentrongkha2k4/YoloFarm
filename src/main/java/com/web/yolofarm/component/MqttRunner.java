package com.web.yolofarm.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.web.yolofarm.service.mqtt.MqttService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MqttRunner implements CommandLineRunner {
    private final MqttService mqttService;
    private final String[] feeds={"nhietdo", "doam", "doamdat", "dosang"};
    @Override
    public void run(String... args) throws Exception {
        for (String feed : feeds) {
            mqttService.startListening(feed);
        }

        
    }

}
