package com.web.yolofarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.web.yolofarm.configuration.MqttProperties;

@SpringBootApplication
@EnableConfigurationProperties(MqttProperties.class)
public class YolofarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(YolofarmApplication.class, args);
	}

}
