package com.achiridev.publico;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/public")
public class EndpointsPublicosController {
    @GetMapping("/info")
    public ResponseEntity<String> infoApp() {
        String mensaje = "PROYECTO 1 — API de Autenticación con JWT (Sistema Base Profesional)";
        return ResponseEntity.ok(mensaje);
    }
    
}
