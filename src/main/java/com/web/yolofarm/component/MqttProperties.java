package com.web.yolofarm.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@ConfigurationProperties(prefix = "adafruit")
public class MqttProperties {
    private String broker;
    private String username;
    private String apikey;
}
