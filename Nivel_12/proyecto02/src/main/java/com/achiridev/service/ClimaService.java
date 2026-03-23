package com.achiridev.service;

import org.springframework.stereotype.Service;
import com.achiridev.Clima;

@Service
public class ClimaService {
    private Clima clima;

    public ClimaService(Clima clima) {
        this.clima = clima;
    }
    public String obtenerClima() {
        return "Llamando a: " + clima.getUrl();
    }
}
