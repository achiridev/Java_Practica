package com.achiridev.service;

import com.achiridev.config.AppConfig;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    private final AppConfig appConfig;

    public InfoService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public AppConfig getInfo() {
        return appConfig;
    }
}
