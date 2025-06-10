package com.copel.Jornada.Fila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fila")
public class FilaController {
    
    @Autowired
    private FilaService filaService;

    @PostMapping("/demandas/redistribuir")
    public ResponseEntity<Void> redistribuir() {
        filaService.redistribuirFilas();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<String> finalizarDemanda(@PathVariable Long id) {
        return filaService.finalizarDemanda(id);
    }

}
