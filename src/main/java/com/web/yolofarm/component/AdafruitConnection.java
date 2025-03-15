package com.web.yolofarm.component;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AdafruitConnection {
    private static volatile MqttClient instance;
    private static MqttProperties mqttProperties;


    public static MqttClient getInstance() throws MqttException {
        if (instance == null || !instance.isConnected()) {
            synchronized (AdafruitConnection.class) {
                if (instance == null || !instance.isConnected()) {
                    instance = new MqttClient(mqttProperties.getBroker(), MqttClient.generateClientId());
                    MqttConnectOptions options = new MqttConnectOptions();
                    options.setUserName(mqttProperties.getUsername());
                    options.setPassword(mqttProperties.getApikey().toCharArray());
                    instance.connect(options);
                }
            }
        }
        return instance;
    }
}
