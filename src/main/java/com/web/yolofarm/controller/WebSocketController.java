package com.web.yolofarm.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.service.mqtt.IMqttService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final IMqttService mqttService;

    @MessageMapping("/maybom") 
    @SendTo("/topic/microbit/maybom") 
    public DeviceActivityLog controlPumper(@Payload DeviceActivityLog message) {
        mqttService.publishData(message.getDeviceName(), message.getAction(), message.getTriggeredBy(), message.getReason());
        return message; // Gửi lại client
    }
    @MessageMapping("/den")
    @SendTo("/topic/microbit/den") 
    public DeviceActivityLog controlLigth(@Payload DeviceActivityLog message) {
        mqttService.publishData(message.getDeviceName(), message.getAction(), message.getTriggeredBy(), message.getReason());
        return message; // Gửi lại client
    }
}
