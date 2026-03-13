package com.achiridev.proyecto03;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationSender notificationSender;

    public NotificationService(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void enviarNotificacion(String mensaje) {
        notificationSender.enviar(mensaje);
    }
}
