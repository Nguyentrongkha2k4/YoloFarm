package com.web.yolofarm.entity;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "scheduled")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceScheduled {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(nullable = false)
    String deviceName; 

    @Column(nullable = false)
    String status; 

    LocalTime startTime;

    Duration intervalTime;

    @Column(nullable = false)
    Duration duration; 
}
