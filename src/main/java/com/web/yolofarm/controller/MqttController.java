package com.web.yolofarm.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/mqtt")
@RequiredArgsConstructor
public class MqttController {
    // private final MqttService mqttService;
    // private final DeviceService deviceService;
    private final SimpMessagingTemplate messagingTemplate;
    // @PostMapping("/control")
    // public ResponseEntity<String> controlDevice(@RequestParam String deviceName, @RequestParam String action, @RequestParam String triggeredBy, @RequestParam(required = false) String reason) {
    //     if (!action.equalsIgnoreCase("on") && !action.equalsIgnoreCase("off")) {
    //         return ResponseEntity.ok().body("Hành động không hợp lệ! Chỉ chấp nhận 'on' hoặc 'off'.");
    //     }

    //     mqttService.publishData(deviceName, action, triggeredBy, reason);
    //     return ResponseEntity.ok().body(deviceName + " đã được " + action + " bởi " + triggeredBy);
    // }

    // @GetMapping("/logs")
    // public ResponseEntity<List<DeviceActivityLog>> getAllDeviceLogs() {
    //     return ResponseEntity.ok().body(deviceService.getAllDeviceLogs());
    // }

    // @PostMapping("/stop/{feed}")
    // public ResponseEntity<String> stopListening(@PathVariable String feed) {
    //     mqttService.stopListeningForFeed(feed);
    //     return ResponseEntity.ok().body("ok");
    // }

    @PostMapping("/{feed}")
    public String postMethodName(@PathVariable(value = "feed") String feed, @RequestParam(name = "value", required = true) String value) {
        
        SensorType sensorType = SensorType.HUMIDITY;
                switch (feed) {
                    case "nhietdo":
                        sensorType = SensorType.TEMPERATURE;
                        break;
                    case "doam":
                    sensorType = SensorType.HUMIDITY;
                        break;
                    case "dosang":
                    sensorType = SensorType.LIGHT;
                        break;
                    case "doamdat":
                    sensorType = SensorType.SOIL_MOISTURE;
                        break;
                        default:
                        return "Error";
                }
        SensorData sensorData = SensorData
                                            .builder()
                                            .type(sensorType)
                                            .value(Float.parseFloat(value))
                                            .recordedAt(LocalDateTime.now())
                                            .build();
        messagingTemplate.convertAndSend("/topic/microbit/"+feed, sensorData);
        return "Success";
    }
    
    
}
