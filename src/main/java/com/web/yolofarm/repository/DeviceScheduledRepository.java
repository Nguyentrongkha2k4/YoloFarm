package com.web.yolofarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.yolofarm.entity.DeviceScheduled;
import java.util.List;


public interface DeviceScheduledRepository extends JpaRepository<DeviceScheduled, Long>{
    boolean existsByDeviceName(String deviceName);
    void deleteByDeviceName(String deviceName);
    DeviceScheduled findByDeviceName(String deviceName);
    List<DeviceScheduled> findAllByStatus(String status);
}
