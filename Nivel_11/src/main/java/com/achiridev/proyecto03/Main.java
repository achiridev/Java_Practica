package com.achiridev.proyecto03;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n🚀 PROYECTO 3 — Sistema de Notificaciones (Email/SMS)\n");
        var context = new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig.class);
        NotificationService notificationService = context.getBean(NotificationService.class);
        notificationService.enviarNotificacion("Inicio de sesion exitosa.");
    }
}
