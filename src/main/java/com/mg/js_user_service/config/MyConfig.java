package com.mg.js_user_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "com.vodafone.dxl")
public class MyConfig {

    private Map<String, List<String>> blackListEndPoints;

    public Map<String, List<String>> getBlackListEndPoints() {
        return blackListEndPoints;
    }

    public void setBlackListEndPoints(Map<String, List<String>> blackListEndPoints) {
        this.blackListEndPoints = blackListEndPoints;
    }
}