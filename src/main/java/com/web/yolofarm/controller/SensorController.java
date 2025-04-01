package com.web.yolofarm.controller;

import com.web.yolofarm.dto.SensorDataDto;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.service.DeviceService;
import com.web.yolofarm.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    // lay tat ca
    @GetMapping("/sensors")
    public ResponseEntity<List<SensorDataDto>> getAllDeviceLogs() {
        return ResponseEntity.ok().body(sensorService.getAllSensorData());
    }
    // lay theo kieu
    @GetMapping("/sensors/{sensorType}")
    public ResponseEntity<List<SensorDataDto>> getLogsByDevice(@PathVariable String sensorType) throws Exception {
        SensorType type = null;
        switch (sensorType) {
            case "nhietdo":
                type = SensorType.TEMPERATURE;
                break;
            case "doam":
                type = SensorType.HUMIDITY;
                break;
            case "dosang":
                type = SensorType.LIGHT;
                break;
            case "doamdat":
                type = SensorType.SOIL_MOISTURE;
                break;
            default:
        }
        return ResponseEntity.ok(sensorService.getLogsByType(type));
    }
    // lay moi nhat cua cac loai cam bien
    @GetMapping("/sensors/latest")
    public ResponseEntity<List<SensorDataDto>> getLatestLogsForPumpAndLight() {
        List<SensorDataDto> log = sensorService.getLatestAllSensorDataByType();
        return ResponseEntity.ok(log);
    }
    // lay moi nhat cua 1 loai cam bien
    @GetMapping("/sensors/latest/{sensorType}")
    public ResponseEntity<SensorDataDto> getLatestLogsByDeviceName(@PathVariable String sensorType) throws Exception {
        SensorType type = null;
        switch (sensorType) {
            case "nhietdo":
                type = SensorType.TEMPERATURE;
                break;
            case "doam":
                type = SensorType.HUMIDITY;
                break;
            case "dosang":
                type = SensorType.LIGHT;
                break;
            case "doamdat":
                type = SensorType.SOIL_MOISTURE;
                break;
            default:
        }
        SensorDataDto log = sensorService.getLatestSensorDataByType(type);
        return ResponseEntity.ok(log);
    }
}
