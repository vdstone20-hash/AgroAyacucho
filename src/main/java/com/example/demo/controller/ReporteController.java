package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.service.ReporteService;
import com.example.demo.repository.UsuarioRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    private final UsuarioRepository usuarioRepository;

    ReporteController(ReporteService reporteService, UsuarioRepository usuarioRepository) {
        this.reporteService = reporteService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<byte[]> descargarReporteUsuarios() {
        try {
            // Obtenemos los usuarios de la base de datos (o puedes mandar una lista de prueba)
            List<Usuario> usuarios = usuarioRepository.findAll();

            // Generamos el PDF
            byte[] pdfBytes = reporteService.generarReporteUsuarios(usuarios);



            List<Usuario> usuarios1 = usuarioRepository.findAll();
System.out.println(">>> CANTIDAD DE USUARIOS OBTENIDOS: " + usuarios1.size());




            // Configuramos los headers para que el navegador descargue/muestre el PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "reporte_usuarios.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}