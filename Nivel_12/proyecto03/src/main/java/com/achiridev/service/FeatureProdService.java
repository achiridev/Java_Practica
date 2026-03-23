package com.achiridev.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class FeatureProdService implements FeatureService {
    
    @Override
    public void ejecutar() {
        System.out.println("Feature desactivada");
    }
}
