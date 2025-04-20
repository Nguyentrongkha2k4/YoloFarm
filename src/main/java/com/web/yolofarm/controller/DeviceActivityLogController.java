package com.web.yolofarm.controller;

import com.web.yolofarm.dto.DeviceLogDto;
import com.web.yolofarm.dto.ResponseObject;
import com.web.yolofarm.service.device.IDeviceService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/devicelog")
@RequiredArgsConstructor
public class DeviceActivityLogController {
    private final IDeviceService deviceService;

    // chi danh cho den va may bom
    @GetMapping("/logs")
    public ResponseObject<List<DeviceLogDto>> getAllDeviceLogs() {
        return ResponseObject.<List<DeviceLogDto>>builder().code(200).status(true).data(deviceService.getAllDeviceLogs()).build();
    }

    @GetMapping("/logs/{deviceName}")
    public ResponseObject<List<DeviceLogDto>> getLogsByDevice(@PathVariable String deviceName) {
        return ResponseObject.<List<DeviceLogDto>>builder().code(200).status(true).data(deviceService.getLogsByDeviceName(deviceName)).build();
    }
    // ban moi nhat cua ca may bom va den
    @GetMapping("/logs/latest")
    public ResponseObject<List<DeviceLogDto>> getLatestLogsForPumpAndLight() {
        List<DeviceLogDto> log = deviceService.getLatestAllLogByDeviceName();
        return ResponseObject.<List<DeviceLogDto>>builder().code(200).status(true).data(log).build();
    }
    // tim ban moi nhat theo ten thiet bi
    @GetMapping("/logs/latest/{deviceName}")
    public ResponseObject<DeviceLogDto> getLatestLogsByDeviceName(@PathVariable String deviceName) {
        DeviceLogDto log = deviceService.getLatestLogByDeviceName(deviceName);
        return ResponseObject.<DeviceLogDto>builder().code(200).status(true).data(log).build();
    }


}
