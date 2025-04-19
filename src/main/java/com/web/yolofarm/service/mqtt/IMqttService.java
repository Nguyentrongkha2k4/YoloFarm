package com.web.yolofarm.service.mqtt;

public interface IMqttService {
    public void startListening(String feed) throws Exception;
    public void publishData(String feed, String action, String triggeredBy, String reason);
    public void stopListeningForFeed(String feed);
    public void checkAndTriggerWatering();
}
