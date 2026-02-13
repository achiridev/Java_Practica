package com.proyecto06;

import com.proyecto06.Repository.HabitacionRepository;
import com.proyecto06.Repository.ReservaRepository;
import com.proyecto06.Service.ReservaService;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 6 — Sistema de reserva con control de concurrencia\n");

        HabitacionRepository habitacionRepo = new HabitacionRepository();
        ReservaRepository reservaRepo = new ReservaRepository();
        ReservaService reservaService = new ReservaService(reservaRepo, habitacionRepo);

        habitacionRepo.findAll().forEach(System.out::println);

            try {
                reservaService.save(2, "Daniel");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        habitacionRepo.findAll().forEach(System.out::println);
    }
}
