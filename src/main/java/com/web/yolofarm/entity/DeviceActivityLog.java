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
    private String deviceName; // Tên thiết bị (Máy bơm, Đèn)

    @Column(nullable = false)
    private String action; // "Bật" hoặc "Tắt"

    private String triggeredBy; // "Người dùng" hoặc "Hệ thống"

    private String reason; // Lý do bật/tắt (nếu có)

    private LocalDateTime timestamp = LocalDateTime.now(); // Thời gian thực hiện
}
