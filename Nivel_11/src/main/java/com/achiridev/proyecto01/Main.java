package com.achiridev.proyecto01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 PROYECTO 1 — Sistema de Conversión de Archivos JSON");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UsuarioController usuarioController = context.getBean(UsuarioController.class);
        String jsonUsuario = """
                {
                    "nombre": "pepito",
                    "email": "pepitoOficial@example.com"
                }
                """;
        String resultado = usuarioController.crearUsuario(jsonUsuario);
        System.out.println("JSON devuelto: " + resultado);
        
        // Cerrar el conext
        ((AnnotationConfigApplicationContext) context).close();
    }
}
