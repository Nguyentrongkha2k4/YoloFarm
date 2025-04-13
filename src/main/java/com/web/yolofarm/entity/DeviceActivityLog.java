package com.web.yolofarm.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device_activity_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceName; 

    @Column(nullable = false)
    private String action; 

    private String triggeredBy; 

    private String reason; 

    private LocalDateTime timestamp = LocalDateTime.now(); 
}
