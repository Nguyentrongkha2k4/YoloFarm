package com.web.yolofarm.controller;

import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.service.ThresholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/threshold")
@RequiredArgsConstructor
public class ThresholdController {
    private final ThresholdService thresholdService;

    @GetMapping("/thresholds")
    public ResponseEntity<List<Threshold>> getAllThresholds() {
        return ResponseEntity.ok(thresholdService.getAllThresholds());
    }

    @PutMapping("/update/{sensorType}")
    public ResponseEntity<Threshold> updateThreshold(
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
        return ResponseEntity.ok(thresholdService.updateThreshold(type, min, max));
    }
}
