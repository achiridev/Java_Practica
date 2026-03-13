package com.achiridev.proyecto02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n🚀 PROYECTO 2 — Sistema de Tareas Concurrentes\n");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TaskService taskService = context.getBean(TaskService.class);
        taskService.ejecutarTareas();
    }
}
