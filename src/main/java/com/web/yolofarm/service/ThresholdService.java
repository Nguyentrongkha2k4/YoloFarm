package com.web.yolofarm.service;

import java.time.LocalDateTime;
import java.util.List;

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
    public static final float TEMPERATURE_MIN = 20;
    public static final float TEMPERATURE_MAX = 40;
    public static final float HUMIDITY_MIN = 40;
    public static final float HUMIDITY_MAX = 60;
    public static final float LIGHT_MIN = 100;
    public static final float LIGHT_MAX = 200;
    public static final float SOIL_MOISTURE_MIN = 30;
    public static final float SOIL_MOISTURE_MAX = 60;
    private final AlertRepository alertRepository;

    public Alert checkThreshold(SensorData sensorData) {
        String message = null;
        SensorType type = sensorData.getType();
        float value = sensorData.getValue();

        switch (type) {
            case TEMPERATURE:
                if (value < TEMPERATURE_MIN) {
                    message = "Nhiệt độ quá thấp! " + value + "C";
                } else if (value > TEMPERATURE_MAX) {
                    message = "Nhiệt độ quá cao! " + value + "C";
                }
                break;
            case HUMIDITY:
                if (value < HUMIDITY_MIN) {
                    message = "Độ ẩm không khí quá thấp! " + value + "%";
                } else if (value > HUMIDITY_MAX) {
                    message = "Độ ẩm không khí quá cao! " + value + "%";
                }
                break;
            case LIGHT:
                if (value < LIGHT_MIN) {
                    message = "Cường độ ánh sáng quá yếu! " + value + " lux";
                } else if (value > LIGHT_MAX) {
                    message = "Cường độ ánh sáng quá mạnh! " + value + " lux";
                }
                break;
            case SOIL_MOISTURE:
                if (value < SOIL_MOISTURE_MIN) {
                    message = "Độ ẩm đất quá thấp! Cần tưới nước. " + value + "%";
                } else if (value > SOIL_MOISTURE_MAX) {
                    message = "Độ ẩm đất quá cao! Tránh tưới thêm nước. " + value + "%";
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid sensor type");
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
    
        switch (sensorData.getType()) {
            case SOIL_MOISTURE:
                if (sensorData.getValue() < 30) {
                    log = DeviceActivityLog.builder()
                            .deviceName("maybom")
                            .action("on")
                            .triggeredBy("System")
                            .reason("Độ ẩm đất thấp")
                            .timestamp(LocalDateTime.now())
                            .build();
                } else if (sensorData.getValue() > 50) {
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
                if (sensorData.getValue() < 50) {
                    log = DeviceActivityLog.builder()
                            .deviceName("den")
                            .action("on")
                            .triggeredBy("System")
                            .reason("Mức sáng thấp")
                            .timestamp(LocalDateTime.now())
                            .build();
                } else if (sensorData.getValue() > 150) {
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
}
