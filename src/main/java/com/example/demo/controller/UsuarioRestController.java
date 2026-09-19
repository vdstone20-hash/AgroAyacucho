package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRestController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}

