package com.achiridev.controller;

import com.achiridev.config.AppConfig;
import com.achiridev.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public AppConfig getInfo() {
        return infoService.getInfo();
    }
}
