package com.web.yolofarm.repository;

import com.web.yolofarm.entity.Threshold;
import com.web.yolofarm.enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdRepository extends JpaRepository<Threshold, SensorType> {
}
