package com.web.yolofarm.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "adafruit")
public class MqttProperties {
    private String username;
    private String adakey;
    private String broker;
}
