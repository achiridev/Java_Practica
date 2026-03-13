package com.achiridev.proyecto02;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;  
import jakarta.annotation.PreDestroy;

@Component
@Scope("prototype")
public class Task {
    private long id;
    private String description;

    public Task() {}
    public Task(long id, String description) {
        this.id = id;
        this.description = description;
        System.out.println("Nueva tarea creada");
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task{id=").append(id)
            .append(", description='").append(description).append('\'')
            .append('}');
        return sb.toString();
    }

    @PostConstruct
    public void init() {
        System.out.println("Tarea inicializada");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Tarea destruida");
    }
}
