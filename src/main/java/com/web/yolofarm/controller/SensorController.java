package com.web.yolofarm.controller;

import com.web.yolofarm.dto.ResponseObject;
import com.web.yolofarm.dto.SensorDataDto;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.service.sensor.ISensorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final ISensorService sensorService;
    // lay tat ca
    @GetMapping("/sensors")
    public ResponseObject<List<SensorDataDto>> getAllDeviceLogs() {
        return ResponseObject.<List<SensorDataDto>>builder().code(200).status(true).data(sensorService.getAllSensorData()).build();
    }
    // lay theo kieu
    @GetMapping("/sensors/{sensorType}")
    public ResponseObject<List<SensorDataDto>> getLogsByDevice(@PathVariable String sensorType) throws Exception {
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
        return ResponseObject.<List<SensorDataDto>>builder().code(200).status(true).data(sensorService.getLogsByType(type)).build();
    }
    // lay moi nhat cua cac loai cam bien
    @GetMapping("/sensors/latest")
    public ResponseObject<List<SensorDataDto>> getLatestLogsForPumpAndLight() {
        List<SensorDataDto> log = sensorService.getLatestAllSensorDataByType();
        return ResponseObject.<List<SensorDataDto>>builder().code(200).status(true).data(log).build();
    }
    // lay moi nhat cua 1 loai cam bien
    @GetMapping("/sensors/latest/{sensorType}")
    public ResponseObject<SensorDataDto> getLatestLogsByDeviceName(@PathVariable String sensorType) throws Exception {
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
        return ResponseObject.<SensorDataDto>builder().code(200).status(true).data(log).build();
    }
}
