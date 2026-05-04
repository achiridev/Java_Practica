package com.achiridev.fixtures;

import com.achiridev.model.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class ProductoFactory {

    public static Producto producto() {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Laptop");
        p.setPrecio(new BigDecimal("2500"));
        p.setStock(100);
        return p;
    }
    public static List<Producto> productos() {
        List<Producto> lista = new ArrayList<>();
        for (int i = 1 ; i <= 10; i++) {
            Producto p = new Producto();
            p.setId(Long.valueOf(i));
            p.setNombre("Producto " + i);
            p.setPrecio(new BigDecimal(i * 100));
            p.setStock(i * 10);
            lista.add(p);
        }
        return lista;
    }

    public static Producto productoSinId() {
        Producto p = producto();
        p.setId(null);
        return p;
    }
}
