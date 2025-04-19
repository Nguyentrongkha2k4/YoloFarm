package com.web.yolofarm.service.threshold;

import java.util.List;

import com.web.yolofarm.entity.Alert;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.enums.SensorType;

public interface IThresholdService {
    public Alert checkThreshold(SensorData sensorData);
    public List<Alert> getAllAlerts();
    public DeviceActivityLog checkAndControlDevice(SensorData sensorData);
    public List<Threshold> getAllThresholds();
    public Threshold updateThreshold(SensorType type, float min, float max);
}
