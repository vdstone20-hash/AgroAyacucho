package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Usamos @Controller en lugar de @RestController para devolver vistas HTML
public class UsuarioController {

    @GetMapping("/")
    public String inicio() {
        return "index"; // Esto busca el archivo index.html en la carpeta templates
    }
}