package com.web.yolofarm.controller;

import com.web.yolofarm.dto.DeviceLogDto;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/devicelog")
@RequiredArgsConstructor
public class DeviceActivityLogController {
    private final DeviceService deviceService;

    // chi danh cho den va may bom
    @GetMapping("/logs")
    public ResponseEntity<List<DeviceLogDto>> getAllDeviceLogs() {
        return ResponseEntity.ok().body(deviceService.getAllDeviceLogs());
    }

    @GetMapping("/logs/{deviceName}")
    public ResponseEntity<List<DeviceLogDto>> getLogsByDevice(@PathVariable String deviceName) {
        return ResponseEntity.ok(deviceService.getLogsByDeviceName(deviceName));
    }
    // ban moi nhat cua ca may bom va den
    @GetMapping("/logs/latest")
    public ResponseEntity<List<DeviceLogDto>> getLatestLogsForPumpAndLight() {
        List<DeviceLogDto> log = deviceService.getLatestAllLogByDeviceName();
        return ResponseEntity.ok(log);
    }
    // tim ban moi nhat theo ten thiet bi
    @GetMapping("/logs/latest/{deviceName}")
    public ResponseEntity<DeviceLogDto> getLatestLogsByDeviceName(@PathVariable String deviceName) {
        DeviceLogDto log = deviceService.getLatestLogByDeviceName(deviceName);
        return ResponseEntity.ok(log);
    }


}
