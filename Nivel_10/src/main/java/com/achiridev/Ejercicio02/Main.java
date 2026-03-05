package com.achiridev.Ejercicio02;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 2 — Importador Masivo de Usuarios (Lista + Validación + Errores)\n");

        String json = """
            [  
                {  
                    "user_name": "Daniel",  
                    "age": 18,  
                    "email": "daniel@email.com",  
                    "birth_date": "2008-02-03"  
                },
                {
                    "user_name": "Pepito",  
                    "age": 99,  
                    "email": "pepitoLopez@email.com",  
                    "birth_date": "1927-03-01"
                },
                {
                    "user_name": "Luis",  
                    "age": 11,  
                    "email": "SanLuis@email.com",  
                    "birth_date": "2014-08-21"
                }
            ]
        """;
        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            List<Usuario> listaUsuarios = mapper.readValue(
                json,
                new TypeReference<List<Usuario>>() {}
            );

            for (int i = 0 ; i < listaUsuarios.size() ; i++) {
                System.out.println("Usuario "+(i+1)+":");
                System.out.println("\tNombre: "+listaUsuarios.get(i).getNombre());
                System.out.println("\tEdad: "+listaUsuarios.get(i).getEdad());
                System.out.println("\tEmail: "+listaUsuarios.get(i).getEmail());
                System.out.println("\tFecha Nacimiento: "+listaUsuarios.get(i).getFechaNacimiento());
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error procesando JSON: " + e.getMessage());
        }
    }
}
