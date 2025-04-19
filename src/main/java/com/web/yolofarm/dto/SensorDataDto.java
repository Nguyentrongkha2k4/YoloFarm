package com.web.yolofarm.dto;

import com.web.yolofarm.enums.SensorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SensorDataDto {
    private SensorType type;

    private Float value;

    private LocalDateTime recordedAt;
}
