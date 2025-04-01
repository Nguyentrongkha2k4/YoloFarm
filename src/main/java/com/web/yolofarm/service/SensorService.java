package com.web.yolofarm.service;

import com.web.yolofarm.dto.DeviceLogDto;
import com.web.yolofarm.dto.SensorDataDto;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.repository.DeviceActivityLogRepository;
import com.web.yolofarm.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorDataRepository sensorDataRepository;

    public List<SensorDataDto> getAllSensorData() {
        return sensorDataRepository.findAll()
                .stream()
                .map(sensorData -> new SensorDataDto(sensorData.getType(),
                        sensorData.getValue(),
                        sensorData.getRecordedAt()))
                .toList();
    }

    public List<SensorDataDto> getLogsByType(SensorType type) {
        return sensorDataRepository.findByType(type)
                .stream()
                .map(sensorData -> new SensorDataDto(sensorData.getType(),
                        sensorData.getValue(),
                        sensorData.getRecordedAt()))
                .toList();
    }

    public SensorDataDto getLatestSensorDataByType(SensorType sensorType) {
        SensorData data = sensorDataRepository.findTopByTypeOrderByRecordedAtDesc(sensorType)
                .orElse(null);
        return data == null ? null : new SensorDataDto(data.getType(), data.getValue(), data.getRecordedAt());
    }

    public List<SensorDataDto> getLatestAllSensorDataByType(){
        SensorData temp = sensorDataRepository.findTopByTypeOrderByRecordedAtDesc(SensorType.TEMPERATURE)
                .orElse(null);
        SensorData hum = sensorDataRepository.findTopByTypeOrderByRecordedAtDesc(SensorType.HUMIDITY)
                .orElse(null);
        SensorData light = sensorDataRepository.findTopByTypeOrderByRecordedAtDesc(SensorType.LIGHT)
                .orElse(null);
        SensorData soil = sensorDataRepository.findTopByTypeOrderByRecordedAtDesc(SensorType.SOIL_MOISTURE)
                .orElse(null);

        List<SensorData> result = new ArrayList<>();
        if (temp != null) result.add(temp);
        if (hum != null) result.add(hum);
        if (light != null) result.add(light);
        if (soil != null) result.add(soil);

        return result.stream()
                .map(SensorData -> new SensorDataDto(SensorData.getType(),
                        SensorData.getValue(),
                        SensorData.getRecordedAt()))
                .toList();
    }

}
