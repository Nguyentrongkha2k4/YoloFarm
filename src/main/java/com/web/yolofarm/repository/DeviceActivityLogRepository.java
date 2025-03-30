package com.web.yolofarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.yolofarm.entity.DeviceActivityLog;

import java.util.List;

public interface DeviceActivityLogRepository extends JpaRepository<DeviceActivityLog, Long> {
    List<DeviceActivityLog> findByDeviceName(String deviceName);

}
