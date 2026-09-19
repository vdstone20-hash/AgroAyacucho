package com.example.demo.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    public byte[] generarReporteUsuarios(List<?> listaUsuarios) throws FileNotFoundException, JRException {
        // 1. Cargar el archivo .jrxml desde la carpeta reports en resources
        InputStream inputStream = getClass().getResourceAsStream("/reports/usuarios.jrxml");
        
        if (inputStream == null) {
            throw new FileNotFoundException("No se encontró el archivo usuarios.jrxml en src/main/resources/reports/");
        }

        // 2. Compilar la plantilla desde el InputStream
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // 3. Pasar los datos como una fuente de colección de beans
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaUsuarios);

        // 4. Parámetros opcionales
        Map<String, Object> parameters = new HashMap<>();

        // 5. Llenar el reporte con los datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 6. Exportar a formato PDF en bytes
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}