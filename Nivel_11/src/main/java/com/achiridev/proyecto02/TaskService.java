package com.achiridev.proyecto02;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    public void ejecutarTareas() {
        Task task1 = new Task(1, "Tarea 1");
        Task task2 = new Task(2, "Tarea 2");
        Task task3 = new Task(3, "Tarea 3");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(task3);
    }
}
