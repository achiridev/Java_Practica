package com.achiridev.Ejercicio03;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 3 — Sistema de Eventos con Configuración Global del ObjectMapper\n");
        ObjectMapper mapper = crearMapperConfigurado();
        try {
            // Serializacion
            String jsonCreado = mapper.writeValueAsString(getListaEvento());
            System.out.println(jsonCreado);
            // Deserializacion
            List<Evento> eventos = mapper.readValue(
                getJSON(),
                new TypeReference<List<Evento>>() {}
            );
            eventos.forEach(evento -> {
                System.out.println("Evento: ");
                System.out.println("\tID: "+evento.getId());
                System.out.println("\tName: "+evento.getName());
                System.out.println("\tFecha: "+evento.getFecha());
                System.out.println("\tMetadata: "+evento.getMetadata());
            });
        }
        catch (JsonProcessingException e) {
            System.out.println("Error procesando JSON: " + e.getMessage());
        }
    }

    public static ObjectMapper crearMapperConfigurado() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static List<Evento> getListaEvento() {
        return List.of(
            new Evento(1001, "Login", LocalDateTime.of(2026, 3, 2, 17, 20), new Metadata("192.168.1.10", "mobile")),
            new Evento(1002, "Loading properties", LocalDateTime.of(2026, 3, 2, 19, 10), new Metadata("192.168.1.10", "mobile")),
            new Evento(1003, "2 tunnels registered", LocalDateTime.of(2026, 3, 2, 19, 42), new Metadata("192.168.1.10", "mobile"))
        );
    }

    public static String getJSON() {
        return """
            [
                {
                    "event_id": 1004,  
                    "name": "Login",  
                    "timestamp": "2026-03-04T15:30:00",  
                    "metadata": {  
                        "ip": "192.168.1.10",  
                        "device": "mobile"  
                    }
                }, {
                    "event_id": 1005,
                    "name": "Change Password",  
                    "timestamp": "2026-03-04T15:34:13",  
                    "metadata": {  
                        "ip": "192.168.1.10",  
                        "device": "mobile"
                    }
                }, {
                    "event_id": 1006,  
                    "name": "Close Sesion",
                    "timestamp": "2026-03-04T15:36:40",
                    "metadata": null
                }, {
                    "event_id": 1007,
                    "name": "Login",
                    "timestamp": "2026-03-04T16:01:01",
                    "metadata": {
                        "ip": "192.168.1.10",
                        "device": "laptop"
                    },
                    "warn": "Device unknown"
                }
            ]
        """;
    }
}
