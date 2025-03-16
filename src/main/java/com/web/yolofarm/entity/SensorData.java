package com.web.yolofarm.entity;

import java.time.LocalDateTime;

import com.web.yolofarm.enums.SensorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensor_data")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType type; // Nhiệt độ, độ ẩm, ánh sáng, độ ẩm đất

    @Column(nullable = false)
    private Float value;

    private LocalDateTime recordedAt;
}
