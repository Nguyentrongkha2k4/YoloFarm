package com.web.yolofarm.configuration;

import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.repository.ThresholdRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ThresholdConfig {
    private final ThresholdRepository thresholdRepository;

    @PostConstruct
    public void initThresholds() {
        System.out.println("[ThresholdInitializer] Kiểm tra và thêm ngưỡng còn thiếu...");

        for (SensorType type : SensorType.values()) {
            boolean exists = thresholdRepository.existsById(type);
            if (!exists) {
                Threshold threshold = switch (type) {
                    case TEMPERATURE -> new Threshold(type, 20, 40);
                    case HUMIDITY -> new Threshold(type, 40, 80);
                    case LIGHT -> new Threshold(type, 50, 400);
                    case SOIL_MOISTURE -> new Threshold(type, 40, 70);
                };
                thresholdRepository.save(threshold);
                System.out.println("Thêm ngưỡng mặc định cho " + type);
            } else {
                System.out.println("Đã có ngưỡng cho " + type);
            }
        }
    }
}

