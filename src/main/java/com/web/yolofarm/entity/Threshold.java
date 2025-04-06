package com.web.yolofarm.entity;

import com.web.yolofarm.enums.SensorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "threshold")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Threshold {
    @Id
    @Enumerated(EnumType.STRING)
    private SensorType type;

    private float min;

    private float max;
}
