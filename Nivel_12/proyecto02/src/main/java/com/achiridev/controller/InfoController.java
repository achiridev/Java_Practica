package com.achiridev.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {
    @Value("${app.nombre}")
    private String nombreApp;

    @GetMapping("/info")
    public String info() {
        return "App: " + nombreApp;
    }
}
