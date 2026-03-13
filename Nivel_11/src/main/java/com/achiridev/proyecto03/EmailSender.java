package com.achiridev.proyecto03;

import org.springframework.stereotype.Component;

@Component
public class EmailSender implements NotificationSender {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando correo electrónico: " + mensaje);
    }
}