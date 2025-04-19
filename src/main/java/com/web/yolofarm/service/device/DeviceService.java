package com.web.yolofarm.service.device;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.web.yolofarm.dto.DeviceLogDto;
import org.springframework.stereotype.Service;

import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.repository.DeviceActivityLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceService implements IDeviceService{
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

    public List<DeviceLogDto> getAllDeviceLogs() {
        return deviceActivityLogRepository.findAll()
                .stream()
                .map(deviceActivityLog -> new DeviceLogDto(deviceActivityLog.getDeviceName(),
                        deviceActivityLog.getAction(),
                        deviceActivityLog.getTriggeredBy(),
                        deviceActivityLog.getReason(),
                        deviceActivityLog.getTimestamp()))
                .toList();
    }

    public List<DeviceLogDto> getLogsByDeviceName(String deviceName) {
        return deviceActivityLogRepository.findByDeviceName(deviceName)
                .stream()
                .map(deviceActivityLog -> new DeviceLogDto(deviceActivityLog.getDeviceName(),
                        deviceActivityLog.getAction(),
                        deviceActivityLog.getTriggeredBy(),
                        deviceActivityLog.getReason(),
                        deviceActivityLog.getTimestamp()))
                .toList();
    }

    public DeviceLogDto getLatestLogByDeviceName(String deviceName) {
        DeviceActivityLog log = deviceActivityLogRepository.findTopByDeviceNameOrderByTimestampDesc(deviceName)
                .orElse(null);
        return log == null ? null : new DeviceLogDto(log.getDeviceName(),
                                                    log.getAction(),
                                                    log.getTriggeredBy(),
                                                    log.getReason(),
                                                    log.getTimestamp());
    }

    public List<DeviceLogDto> getLatestAllLogByDeviceName(){
        DeviceActivityLog pumpLog = deviceActivityLogRepository.findTopByDeviceNameOrderByTimestampDesc("maybom")
                .orElse(null);
        DeviceActivityLog lightLog = deviceActivityLogRepository.findTopByDeviceNameOrderByTimestampDesc("den")
                .orElse(null);

        List<DeviceActivityLog> result = new ArrayList<>();
        if (pumpLog != null) result.add(pumpLog);
        if (lightLog != null) result.add(lightLog);

        return result.stream()
                    .map(deviceActivityLog -> new DeviceLogDto(deviceActivityLog.getDeviceName(),
                            deviceActivityLog.getAction(),
                            deviceActivityLog.getTriggeredBy(),
                            deviceActivityLog.getReason(),
                            deviceActivityLog.getTimestamp()))
                    .toList();
    }

}