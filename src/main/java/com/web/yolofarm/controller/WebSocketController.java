package com.web.yolofarm.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.web.yolofarm.entity.DeviceActivityLog;

@Controller
public class WebSocketController {

    @MessageMapping("/maybom") 
    @SendTo("/topic/microbit/maybom") 
    public DeviceActivityLog controlPumper(@Payload DeviceActivityLog message) {
        
        return message; // Gửi lại client
    }
    @MessageMapping("/den")
    @SendTo("/topic/microbit/den") 
    public DeviceActivityLog controlLigth(@Payload DeviceActivityLog message) {
        
        return message; // Gửi lại client
    }
}
