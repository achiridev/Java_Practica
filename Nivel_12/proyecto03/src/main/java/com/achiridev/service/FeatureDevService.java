package com.achiridev.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class FeatureDevService implements FeatureService {

    @Override
    public void ejecutar() {
        System.out.println("Feature experimental activa");
    }
    
}
