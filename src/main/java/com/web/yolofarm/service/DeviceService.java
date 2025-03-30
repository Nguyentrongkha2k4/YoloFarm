package com.web.yolofarm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.repository.DeviceActivityLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceActivityLogRepository deviceActivityLogRepository;

    public void logDeviceActivity(String deviceName, String action, String triggeredBy, String reason) {
        deviceActivityLogRepository.save(
            DeviceActivityLog.builder()
                .deviceName(deviceName)
                .action(action)
                .triggeredBy(triggeredBy)
                .reason(reason)
                .timestamp(LocalDateTime.now())
                .build()
        );
    }

    public List<DeviceActivityLog> getAllDeviceLogs() {
        return deviceActivityLogRepository.findAll();
    }

    public List<DeviceActivityLog> getLogsByDeviceName(String deviceName) {
        return deviceActivityLogRepository.findByDeviceName(deviceName);
    }


}
