package com.web.yolofarm.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeviceLogDto {
    private String deviceName;

    private String action;

    private String triggeredBy;

    private String reason;

    private LocalDateTime timestamp;
}
