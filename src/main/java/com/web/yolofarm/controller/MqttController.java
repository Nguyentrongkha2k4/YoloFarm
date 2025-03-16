package com.web.yolofarm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.service.DeviceService;
import com.web.yolofarm.service.MqttService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mqtt")
@RequiredArgsConstructor
public class MqttController {
    private final MqttService mqttService;
    private final DeviceService deviceService;
    @PostMapping("/control")
    public ResponseEntity<String> controlDevice(@RequestParam String deviceName, @RequestParam String action, @RequestParam String triggeredBy, @RequestParam(required = false) String reason) {
        if (!action.equalsIgnoreCase("on") && !action.equalsIgnoreCase("off")) {
            return ResponseEntity.ok().body("Hành động không hợp lệ! Chỉ chấp nhận 'on' hoặc 'off'.");
        }

        mqttService.publishData(deviceName, action, triggeredBy, reason);
        return ResponseEntity.ok().body(deviceName + " đã được " + action + " bởi " + triggeredBy);
    }

    @GetMapping("/logs")
    public ResponseEntity<List<DeviceActivityLog>> getAllDeviceLogs() {
        return ResponseEntity.ok().body(deviceService.getAllDeviceLogs());
    }

    @PostMapping("/stop/{feed}")
    public ResponseEntity<String> stopListening(@PathVariable String feed) {
        mqttService.stopListeningForFeed(feed);
        return ResponseEntity.ok().body("ok");
    }
}
