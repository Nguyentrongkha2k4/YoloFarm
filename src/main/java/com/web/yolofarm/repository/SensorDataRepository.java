package com.web.yolofarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findByType(SensorType type);
}