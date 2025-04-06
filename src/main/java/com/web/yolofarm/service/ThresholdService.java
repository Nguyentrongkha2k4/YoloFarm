package com.web.yolofarm.service;

import java.time.LocalDateTime;
import java.util.List;

import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.repository.ThresholdRepository;
import org.springframework.stereotype.Service;
import com.web.yolofarm.component.AdafruitConnection;
import com.web.yolofarm.entity.Alert;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.repository.AlertRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThresholdService {
    private final ThresholdRepository thresholdRepository;
    private final AlertRepository alertRepository;

    public Alert checkThreshold(SensorData sensorData) {
        Threshold threshold = thresholdRepository.findById(sensorData.getType())
                .orElseThrow(() -> new RuntimeException("Không tồn tại " + sensorData.getType()));
        String message = null;
        SensorType type = sensorData.getType();
        float value = sensorData.getValue();

        if (value < threshold.getMin()) {
            message = sensorData.getType() + " quá thấp! " + value;
        } else if (value > threshold.getMax()) {
            message = sensorData.getType() + " quá cao! " + value;
        }

        if (message != null) {
            return alertRepository.save(
                Alert.builder()
                    .type(type)
                    .value(value)
                    .message(message)
                    .createdAt(LocalDateTime.now())
                    .build()
            );
        }
        return null;
    }

    public List<Alert> getAllAlerts(){
        return alertRepository.findAll();
    }

    public DeviceActivityLog checkAndControlDevice(SensorData sensorData) {
        DeviceActivityLog log = null;
        SensorType type = sensorData.getType();
        float value = sensorData.getValue();

        Threshold threshold = thresholdRepository.findById(sensorData.getType())
                .orElseThrow(() -> new RuntimeException("Không tồn tại " + type));
    
        switch (sensorData.getType()) {
            case SOIL_MOISTURE:
                if (sensorData.getValue() < threshold.getMin()) {
                    log = DeviceActivityLog.builder()
                            .deviceName("maybom")
                            .action("on")
                            .triggeredBy("System")
                            .reason("Độ ẩm đất thấp")
                            .timestamp(LocalDateTime.now())
                            .build();
                } else if (sensorData.getValue() > threshold.getMax()) {
                    log = DeviceActivityLog.builder()
                            .deviceName("maybom")
                            .action("off")
                            .triggeredBy("System")
                            .reason("Độ ẩm đất đạt mức đủ")
                            .timestamp(LocalDateTime.now())
                            .build();
                }
                break;
            case LIGHT:
                if (sensorData.getValue() < threshold.getMin()) {
                    log = DeviceActivityLog.builder()
                            .deviceName("den")
                            .action("on")
                            .triggeredBy("System")
                            .reason("Mức sáng thấp")
                            .timestamp(LocalDateTime.now())
                            .build();
                } else if (sensorData.getValue() > threshold.getMax()) {
                    log = DeviceActivityLog.builder()
                            .deviceName("den")
                            .action("off")
                            .triggeredBy("System")
                            .reason("Mức sáng đủ")
                            .timestamp(LocalDateTime.now())
                            .build();
                }
                break;
            default:
                break;
        }
        return log;
    }

    public List<Threshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    public Threshold updateThreshold(SensorType type, float min, float max) {
        Threshold threshold = thresholdRepository.findById(type)
                .orElseThrow(() -> new RuntimeException("Không tồn tại " + type));
        threshold.setMin(min);
        threshold.setMax(max);
        return thresholdRepository.save(threshold);
    }
}
