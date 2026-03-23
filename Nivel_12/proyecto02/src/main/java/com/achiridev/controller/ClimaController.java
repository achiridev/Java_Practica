package com.achiridev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achiridev.service.*;

@RestController
@RequestMapping("/api")
public class ClimaController {
    private ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping("/clima")
    public String clima() {
        return climaService.obtenerClima();
    }

}
