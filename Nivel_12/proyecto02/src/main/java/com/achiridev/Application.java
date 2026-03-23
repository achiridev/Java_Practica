package com.achiridev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("\nPROYECTO 2 — Cliente de API Externa configurable\n");
		SpringApplication.run(Application.class, args);
		System.out.println("API de clima disponible en: http://localhost:8080/api/clima\n");
	}

}
