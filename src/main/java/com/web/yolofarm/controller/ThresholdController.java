package com.web.yolofarm.controller;

import com.web.yolofarm.dto.ResponseObject;
import com.web.yolofarm.entity.Alert;
import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.service.threshold.IThresholdService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/threshold")
@RequiredArgsConstructor
public class ThresholdController {
    private final IThresholdService thresholdService;

    @GetMapping("/thresholds")
    public ResponseObject<List<Threshold>> getAllThresholds() {
        return ResponseObject.<List<Threshold>>builder().code(200).status(true).data(thresholdService.getAllThresholds()).build();
    }

    @PutMapping("/update/{sensorType}")
    public ResponseObject<Threshold> updateThreshold(
            @PathVariable String sensorType,
            @RequestParam float min,
            @RequestParam float max
    ) {
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
        return ResponseObject.<Threshold>builder().code(200).status(true).data(thresholdService.updateThreshold(type, min, max)).build();
    }

    @GetMapping("/alerts")
    public ResponseObject<List<Alert>> getAllAlerts() {
        ResponseObject<List<Alert>> responseObject = ResponseObject.<List<Alert>>builder().code(200).status(true).data(thresholdService.getAllAlerts()).build();
        return responseObject;
    }
    
}
