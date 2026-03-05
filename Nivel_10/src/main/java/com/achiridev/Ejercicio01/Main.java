package com.achiridev.Ejercicio01;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 1 — Sistema de Órdenes con Historial y Fechas\n");

        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        serializacion(mapper);

        deserializacion(mapper);

    }
    public static void serializacion(ObjectMapper mapper) {
        List<Producto> lista = List.of(
            new Producto("Martillo", 12.00, 2),
            new Producto("Clavo 1kg", 6.50, 5),
            new Producto("Escoba Tiburon", 10.000, 1)
        );
        Orden orden = new Orden(1L, "Daniel", lista, 66.50, null, "Las notas son...");
        

        
        String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orden);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.out.println("Error procesando JSON: " + e.getMessage());
        }
    }
    public static void deserializacion(ObjectMapper mapper) {
        String json = """
            {
                "id" : 2,
                "nombre_cliente" : "Pepito",
                "fechaCreacion" : "2026-03-05T14:25:55.332459959",
                "productos" : [
                        {
                            "nombre" : "Balde",
                            "precio" : 8.0,
                            "cantidad" : 3
                        }, {
                            "nombre" : "Guante gris",
                            "precio" : 3.0,
                            "cantidad" : 2
                        }, {
                            "nombre" : "Reflon",
                            "precio" : 1.0,
                            "cantidad" : 8
                        }
                    ],
                "total" : 38.0,
                "estado" : null
            }
        """;
        try {
            Orden orden = mapper.readValue(json, Orden.class);
            System.out.println(orden.getId());
            System.out.println(orden.getCliente());
            System.out.println(orden.getFechaCreacion());
            System.out.println(orden.getProductos().toString());
            System.out.println(orden.getTotal());
            System.out.println(orden.getEstado());
            System.out.println(orden.getNotasInternas());
        }
        catch (JsonProcessingException e) {
            System.out.println("Error procesando JSON: " + e.getMessage());
        }
    }
}