package com.web.yolofarm.dto.request;

import java.time.Duration;
import java.time.LocalTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceScheduledCreationRequest {
    
    String deviceName; 

    String status; 

    LocalTime startTime;

    Duration intervalTime;
    
    Duration duration; 
}
