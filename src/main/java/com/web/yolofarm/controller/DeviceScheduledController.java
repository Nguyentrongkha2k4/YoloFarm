package com.web.yolofarm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.yolofarm.dto.ResponseObject;
import com.web.yolofarm.dto.request.DeviceScheduledCreationRequest;
import com.web.yolofarm.entity.DeviceScheduled;
import com.web.yolofarm.service.deviceScheduled.IDeviceScheduledService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/DeviceScheduled")
@RequiredArgsConstructor
public class DeviceScheduledController {
    private final IDeviceScheduledService DeviceScheduledService;

    @PostMapping("/create")
    public ResponseObject<DeviceScheduled> postMethodName(@RequestBody DeviceScheduledCreationRequest request) {
        ResponseObject<DeviceScheduled> responseObject = ResponseObject.<DeviceScheduled>builder().code(200)
                                                                .status(true)
                                                                .data(DeviceScheduledService.create(request))
                                                                .build();
        return responseObject;
    }

    @GetMapping("/get/{deviceName}")
    public ResponseObject<DeviceScheduled> getMethodName(@PathVariable String deviceName) {
        ResponseObject<DeviceScheduled> responseObject = ResponseObject.<DeviceScheduled>builder().code(200)
                                                                .status(true)
                                                                .data(DeviceScheduledService.getDeviceScheduled(deviceName))
                                                                .build();
        return responseObject;
    }

    @PostMapping("/enable/{deviceName}")
    public ResponseObject<DeviceScheduled> enableDeviceScheduled(@PathVariable String deviceName) {
        ResponseObject<DeviceScheduled> responseObject = ResponseObject.<DeviceScheduled>builder().code(200)
                                                                .status(true)
                                                                .data(DeviceScheduledService.enableDeviceScheduled(deviceName))
                                                                .build();
        return responseObject;
    }
    
    @PutMapping("update/{id}")
    public ResponseObject<DeviceScheduled> putMethodName(@PathVariable String id, @RequestBody DeviceScheduledCreationRequest request) {
        ResponseObject<DeviceScheduled> responseObject = ResponseObject.<DeviceScheduled>builder().code(200)
                                                                .status(true)
                                                                .data(DeviceScheduledService.update(id, request))
                                                                .build();
        return responseObject;
    }
}
