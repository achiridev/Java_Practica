package com.proyecto05;

import java.util.List;

import com.proyecto05.Repository.OrdenRepository;
import com.proyecto05.Repository.Orden_itemsRepository;
import com.proyecto05.Repository.ProductoRepository;
import com.proyecto05.Service.OrdenService;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPROYECTO 5 — Creación de pedido con múltiples inserts dependientes\n");

        ProductoRepository productoRepo = new ProductoRepository();
        OrdenService ordenService = new OrdenService(
            new Orden_itemsRepository(),
            new OrdenRepository(),
            productoRepo
        );
        String usuario = "Daniel";
        List<ItemDTO> items = List.of(
            new ItemDTO(10, 2, 3.00),
            new ItemDTO(4, 1, 3.50)
        );

        System.out.println("Lista de productos: ");
        productoRepo.findAll().forEach(System.out::println);

        ordenService.save(usuario, items);

        System.out.println("Lista de productos: ");
        productoRepo.findAll().forEach(System.out::println);
    }
}
