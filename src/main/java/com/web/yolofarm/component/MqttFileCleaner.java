package com.web.yolofarm.component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MqttFileCleaner {
    public static void deleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deleteRecursive(subFile);
            }
        }
        file.delete();
    }
    
    public static void cleanMqttTempFiles() {
        String userHome = System.getProperty("user.home");
        Path mqttTempPath = Paths.get(userHome, ".mvn");

        if (Files.exists(mqttTempPath)) {
            File folder = mqttTempPath.toFile();
            for (File file : folder.listFiles()) {
                if (file.getName().startsWith("paho")) {
                    deleteRecursive(file);
                    System.out.println("✅ Đã xóa: " + file.getName());
                }
            }
        }
    }
}
