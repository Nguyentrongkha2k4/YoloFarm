package com.web.yolofarm.service.sensor;

import java.util.List;

import com.web.yolofarm.dto.SensorDataDto;
import com.web.yolofarm.enums.SensorType;

public interface ISensorService {
    public List<SensorDataDto> getAllSensorData();
    public List<SensorDataDto> getLogsByType(SensorType type);
    public SensorDataDto getLatestSensorDataByType(SensorType sensorType);
    public List<SensorDataDto> getLatestAllSensorDataByType();
    
}
