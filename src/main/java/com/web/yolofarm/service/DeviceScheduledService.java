package com.web.yolofarm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.yolofarm.dto.request.DeviceScheduledCreationRequest;
import com.web.yolofarm.entity.DeviceScheduled;
import com.web.yolofarm.exception.AppException;
import com.web.yolofarm.exception.ErrorCode;
import com.web.yolofarm.repository.DeviceScheduledRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceScheduledService {
    private final DeviceScheduledRepository deviceScheduledRepository;

    public DeviceScheduled create(DeviceScheduledCreationRequest request){
        if (deviceScheduledRepository.existsByDeviceName(request.getDeviceName())){
            deviceScheduledRepository.deleteByDeviceName(request.getDeviceName());
        }

        DeviceScheduled deviceScheduled = DeviceScheduled.builder()
                                        .deviceName(request.getDeviceName())
                                        .startTime(request.getStartTime())
                                        .intervalTime(request.getIntervalTime())
                                        .duration(request.getDuration())
                                        .status("ACTIVE")
                                        .build();
        
        return deviceScheduledRepository.save(deviceScheduled);
    }

    public DeviceScheduled getDeviceScheduled(String request){
        return deviceScheduledRepository.findByDeviceName(request);
    }

    public DeviceScheduled enableDeviceScheduled(String request){
        DeviceScheduled DeviceScheduled = deviceScheduledRepository.findByDeviceName(request);

        DeviceScheduled.setStatus("INACTIVE");
        
        return deviceScheduledRepository.save(DeviceScheduled);
    }

    public DeviceScheduled update(String id, DeviceScheduledCreationRequest request){
        DeviceScheduled DeviceScheduled = deviceScheduledRepository.findById(Long.parseLong(id)).orElseThrow(()-> new AppException(ErrorCode.UNCATEGORIED_EXCEPTION));

        DeviceScheduled.setStartTime(request.getStartTime());
        DeviceScheduled.setIntervalTime(request.getIntervalTime());
        DeviceScheduled.setDuration(request.getDuration());
        
        return deviceScheduledRepository.save(DeviceScheduled);
    }

    public List<DeviceScheduled> findAllByEnable(){
        return deviceScheduledRepository.findAllByStatus("ACTIVE");
    }
}
