package com.web.yolofarm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.web.yolofarm.service.MqttService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mqtt/publish")
@RequiredArgsConstructor
public class MqttController {
    private final MqttService mqttService;

    @PostMapping("/send")
    public ResponseEntity<String> sendData(@RequestParam(name = "data", required = true) String data, @RequestParam(name = "feed", required = true) String feed) {
        mqttService.publishData(data, feed);
        return ResponseEntity.ok().body("Đã gửi dữ liệu: "+ data + " tới feed: " + feed);
    }

    // @PostMapping("/{feed}")
    // public ResponseEntity<String> getMethodName(@PathVariable String feed) {
    //     try {
    //         mqttService.startListening(feed);
    //         return ResponseEntity.ok().body("ok");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body("Error");
    //     }
    // }

    @PostMapping("/stop/{feed}")
    public ResponseEntity<String> stopListening(@PathVariable String feed) {
        mqttService.stopListeningForFeed(feed);
        return ResponseEntity.ok().body("ok");
    }
}
