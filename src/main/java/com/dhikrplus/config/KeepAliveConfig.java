package com.dhikrplus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
public class KeepAliveConfig {

    private static final Logger log = LoggerFactory.getLogger(KeepAliveConfig.class);

    // Ping toutes les 10 minutes pour éviter le sleep Render
    @Scheduled(fixedRate = 600000)
    public void keepAlive() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(
                "https://dhikr-plus-backend.onrender.com/api/dhikrs",
                String.class
            );
            log.info("Keep-alive ping envoyé avec succès");
        } catch (Exception e) {
            log.warn("Keep-alive ping échoué: {}", e.getMessage());
        }
    }
}