package com.web.yolofarm.controller;

import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/devicelog")
@RequiredArgsConstructor
public class DeviceActivityLogController {
    private final DeviceService deviceService;

    @GetMapping("/logs")
    public ResponseEntity<List<DeviceActivityLog>> getAllDeviceLogs() {
        return ResponseEntity.ok().body(deviceService.getAllDeviceLogs());
    }

    @GetMapping("/logs/{deviceName}")
    public ResponseEntity<List<DeviceActivityLog>> getLogsByDevice(@PathVariable String deviceName) {
        return ResponseEntity.ok(deviceService.getLogsByDeviceName(deviceName));
    }


}
