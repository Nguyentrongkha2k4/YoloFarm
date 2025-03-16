package com.web.yolofarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.yolofarm.entity.DeviceActivityLog;

public interface DeviceActivityLogRepository extends JpaRepository<DeviceActivityLog, Long> {
}
