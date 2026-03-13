package com.achiridev.proyecto03;

import org.springframework.stereotype.Component;

@Component
public class SmsSender implements NotificationSender {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando mensaje de texto: " + mensaje);
    }
}
