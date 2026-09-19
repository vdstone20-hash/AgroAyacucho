package com.example.demo.controller;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
}
