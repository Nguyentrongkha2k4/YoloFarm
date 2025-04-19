package com.web.yolofarm.service.mqtt;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.web.yolofarm.component.AdafruitConnection;
import com.web.yolofarm.configuration.MqttProperties;
import com.web.yolofarm.entity.Alert;
import com.web.yolofarm.entity.DeviceActivityLog;
import com.web.yolofarm.entity.DeviceScheduled;
import com.web.yolofarm.entity.SensorData;
import com.web.yolofarm.enums.SensorType;
import com.web.yolofarm.repository.SensorDataRepository;
import com.web.yolofarm.service.device.IDeviceService;
import com.web.yolofarm.service.deviceScheduled.IDeviceScheduledService;
import com.web.yolofarm.service.threshold.IThresholdService;

@Service
public class MqttService implements IMqttService{
    private MqttClient CLIENT;
    @Value("${adafruit.username}")
    private String username;
    private final String TOPIC = "/feeds/";
    private final SimpMessagingTemplate messagingTemplate;
    private final SensorDataRepository sensorDataRepository;
    private final IThresholdService thresholdService;
    private final IDeviceService deviceService;
    private final IDeviceScheduledService DeviceScheduledService;

    public MqttService(IDeviceScheduledService DeviceScheduledService,MqttProperties properties, SimpMessagingTemplate messagingTemplate, SensorDataRepository sensorDataRepository, IThresholdService thresholdService, IDeviceService deviceService) throws MqttSecurityException, MqttException {
        this.DeviceScheduledService = DeviceScheduledService;
        this.messagingTemplate = messagingTemplate;
        this.sensorDataRepository = sensorDataRepository;
        this.thresholdService = thresholdService;
        this.deviceService = deviceService;
        CLIENT = AdafruitConnection.getInstance(properties);

        // Đảm bảo client đã kết nối trước khi subscribe
        if (!CLIENT.isConnected()) {
            CLIENT.connect();
        }
    }

    public void startListening(String feed) throws Exception {
        String topic = username + TOPIC + feed;
        try {
            CLIENT.subscribe(topic, (t, message) -> {
                // TODO: Xử lý dữ liệu tại đây (lưu vào DB, hiển thị UI, ...)
                String payload = new String(message.getPayload());
                SensorType sensorType = SensorType.HUMIDITY;
                switch (feed) {
                    case "nhietdo":
                        sensorType = SensorType.TEMPERATURE;
                        break;
                    case "doam":
                    sensorType = SensorType.HUMIDITY;
                        break;
                    case "dosang":
                    sensorType = SensorType.LIGHT;
                        break;
                    case "doamdat":
                    sensorType = SensorType.SOIL_MOISTURE;
                        break;
                        default:
                        throw new Exception("Feed is invalid");
                }
                SensorData sensorData = SensorData
                                            .builder()
                                            .type(sensorType)
                                            .value(Float.parseFloat(payload))
                                            .recordedAt(LocalDateTime.now())
                                            .build();
                sensorDataRepository.save(sensorData);
                System.out.println("Receive from" + t + ": " + sensorData);
                
                // Gửi dữ liệu đến client qua websocket
                messagingTemplate.convertAndSend("/topic/microbit/"+feed, sensorData);

                //Gửi thông báo nếu giá trị vượt ngưỡng
                Alert alert = thresholdService.checkThreshold(sensorData);
                if (alert != null){
                    messagingTemplate.convertAndSend("/topic/microbit/limit", alert);
                }

                System.out.println("alert data: " + alert);
                DeviceActivityLog device_activity_log = thresholdService.checkAndControlDevice(sensorData);
                if (device_activity_log != null){
                    messagingTemplate.convertAndSend("/topic/microbit/"+device_activity_log.getDeviceName(), device_activity_log);
                    new Thread(() -> publishData(
                                        device_activity_log.getDeviceName(),
                                        device_activity_log.getAction(),
                                        device_activity_log.getTriggeredBy(),
                                        device_activity_log.getReason()
                                    )).start();
                }
                System.out.println("activities data: " + device_activity_log);

            });
        } catch (MqttException e) {
            System.err.println("Error when subcrice: " + e.getReasonCode());
        }

    }

    public void publishData(String feed, String action, String triggeredBy, String reason) {
        try {
            String data = "0";
            if (action.equalsIgnoreCase("on")) {
                data = "1";
            }
            String topic = username + TOPIC + feed;
            MqttMessage message = new MqttMessage(data.getBytes());
            message.setQos(0);
            CLIENT.publish(topic, message);
            deviceService.logDeviceActivity(feed, action, triggeredBy, reason);
            System.out.println("Sent data: " + data);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void stopListeningForFeed(String feed) {
        try {
            if (CLIENT != null && CLIENT.isConnected()) {
                String topic = username + TOPIC + feed;
                CLIENT.unsubscribe(topic);
                System.out.println("Disconnected with: " + topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 60000)
    public void checkAndTriggerWatering() {
        List<DeviceScheduled> schedules = DeviceScheduledService.findAllByEnable();
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);
    
        for (DeviceScheduled schedule : schedules) {
            LocalTime startTime = schedule.getStartTime().withSecond(0).withNano(0);
            Duration intervalTime = schedule.getIntervalTime();
            Duration duration = schedule.getDuration();
    
            long minutesSinceStart = Duration.between(startTime, now).toMinutes();
            if (minutesSinceStart < 0) continue; // chưa đến giờ bắt đầu
    
            long intervalTimesPassed = minutesSinceStart / intervalTime.toMinutes();
            LocalTime currentCycleStart = startTime.plusMinutes(intervalTime.toMinutes() * intervalTimesPassed);
            LocalTime currentCycleEnd = currentCycleStart.plus(duration);
    
            if (!now.isBefore(currentCycleStart) && now.isBefore(currentCycleEnd)) {
                // Trong thời gian tưới
                try {
                    publishData(schedule.getDeviceName(), "on", "SYSTEM", "Scheduled");
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }
            } else {
                // Ngoài thời gian tưới
                try {
                    publishData(schedule.getDeviceName(), "off", "SYSTEM", "Scheduled");
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }
            }
        }
    }
}
