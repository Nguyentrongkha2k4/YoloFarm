package com.web.yolofarm.service;

import org.eclipse.paho.client.mqttv3.MqttClient;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.stereotype.Service;

import com.web.yolofarm.component.AdafruitConnection;
import com.web.yolofarm.configuration.MqttProperties;

@Service
public class MqttService {
    private MqttClient CLIENT;
    private final String TOPIC = "khanguyentrong2k4/feeds/";

    public MqttService(MqttProperties properties) throws MqttSecurityException, MqttException {
        CLIENT = AdafruitConnection.getInstance(properties);

        // Đảm bảo client đã kết nối trước khi subscribe
        if (!CLIENT.isConnected()) {
            CLIENT.connect();
        }
    }

    public void startListening(String feed) throws Exception {
        String topic = TOPIC + feed;

        try {
            CLIENT.subscribe(topic, (t, message) -> {
                String payload = new String(message.getPayload());
                System.out.println("Nhận dữ liệu từ " + t + ": " + payload);

                // TODO: Xử lý dữ liệu tại đây (lưu vào DB, hiển thị UI, ...)
            });
        } catch (MqttException e) {
            System.err.println("Lỗi khi subscribe: " + e.getReasonCode());
        }

    }

    public void publishData(String data, String feed) {
        try {
            String topic = TOPIC + feed;
            MqttMessage message = new MqttMessage(data.getBytes());
            message.setQos(0);
            CLIENT.publish(topic, message);
            System.out.println("Đã gửi dữ liệu: " + data);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void stopListeningForFeed(String feed) {
        try {
            if (CLIENT != null && CLIENT.isConnected()) {
                String topic = TOPIC + feed;
                CLIENT.unsubscribe(topic);
                System.out.println("Đã hủy đăng ký khỏi feed: " + topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
