package com.achiridev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.achiridev.service.FeatureService;

@RestController
@RequestMapping("/api")
public class FeatureController {
    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("/feature")
    public String activarFeature() {
        featureService.ejecutar();
        return "Feature ejecutada";
    }
}
