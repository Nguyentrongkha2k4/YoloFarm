package com.web.yolofarm.service;

import com.web.yolofarm.configuration.MqttProperties;
import com.web.yolofarm.dto.response.DeviceStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.Map;


@Service
@Component
@RequiredArgsConstructor
public class DeviceStatusService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final MqttProperties mqttProperties;

    private String getLastValueFromFeed(String feed) {
        String url = "https://io.adafruit.com/api/v2/" +
                mqttProperties.getUsername() +
                "/feeds/" + feed + "/data/last";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-AIO-Key", mqttProperties.getAdaaccess());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, Map.class);
            return (String) response.getBody().get("value");
        } catch (Exception e) {
            return "unknown";
        }
    }

    public Map<String, String> getAllStatuses() {
        Map<String, String> result = new LinkedHashMap<>();

        // Sensor readings
        result.put("nhietdo", getLastValueFromFeed("nhietdo"));
        result.put("doam", getLastValueFromFeed("doam"));
        result.put("doamdat", getLastValueFromFeed("doamdat"));
        result.put("dosang", getLastValueFromFeed("dosang"));

        // Device statuses (0 = off, 1 = on)
        String maybom = getLastValueFromFeed("maybom");
        String den = getLastValueFromFeed("den");

        result.put("maybom", "1".equals(maybom) ? "on" : "off");
        result.put("den", "1".equals(den) ? "on" : "off");

        return result;
    }

}
