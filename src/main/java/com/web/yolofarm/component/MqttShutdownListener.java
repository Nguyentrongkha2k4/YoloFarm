package com.web.yolofarm.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.web.yolofarm.service.mqtt.MqttService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MqttShutdownListener implements ApplicationListener<ContextClosedEvent> {
    private final MqttService mqttService;
    private final String[] feeds={"nhietdo", "doam", "doamdat", "dosang"};
    @Override
    public void onApplicationEvent(@SuppressWarnings("null") ContextClosedEvent event) {
        for (String feed : feeds) {
            mqttService.stopListeningForFeed(feed);
        }
        // MqttFileCleaner.cleanMqttTempFiles();
    }
}
