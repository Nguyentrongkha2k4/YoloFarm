package com.web.yolofarm.controller;

import com.web.yolofarm.service.DeviceStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/device_status")
@RequiredArgsConstructor
public class DeviceStatusController {
    private final DeviceStatusService deviceStatusService;

    @GetMapping("/devices")
    public ResponseEntity<Map<String, String>> getAllStatus() {
        return ResponseEntity.ok(deviceStatusService.getAllStatuses());
    }
}
