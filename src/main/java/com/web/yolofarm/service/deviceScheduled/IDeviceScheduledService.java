package com.web.yolofarm.service.deviceScheduled;

import java.util.List;

import com.web.yolofarm.dto.request.DeviceScheduledCreationRequest;
import com.web.yolofarm.entity.DeviceScheduled;

public interface IDeviceScheduledService {
    public DeviceScheduled create(DeviceScheduledCreationRequest request);
    public DeviceScheduled getDeviceScheduled(String request);
    public DeviceScheduled enableDeviceScheduled(String request);
    public DeviceScheduled update(String id, DeviceScheduledCreationRequest request);
    public List<DeviceScheduled> findAllByEnable();
}
