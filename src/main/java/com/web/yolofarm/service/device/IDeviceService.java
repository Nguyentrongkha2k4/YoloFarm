package com.web.yolofarm.service.device;

import java.util.List;

import com.web.yolofarm.dto.DeviceLogDto;

public interface IDeviceService {
    public void logDeviceActivity(String deviceName, String action, String triggeredBy, String reason);
    public List<DeviceLogDto> getAllDeviceLogs();
    public List<DeviceLogDto> getLogsByDeviceName(String deviceName);
    public DeviceLogDto getLatestLogByDeviceName(String deviceName);
    public List<DeviceLogDto> getLatestAllLogByDeviceName();
}
