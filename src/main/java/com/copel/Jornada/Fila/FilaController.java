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
        String resultado = filaService.finalizarDemanda(id);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/visualizar/on-holding")
    public ResponseEntity<String> visualizarOnHolding() {
        String resultado = filaService.visualizarOnHolding();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/visualizar/on-going")
    public ResponseEntity<String> visualizarOnGoing() {
        String resultado = filaService.visualizarOnGoing();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/visualizar/is-executing")
    public ResponseEntity<String> visualizarIsExecuting() {
        String resultado = filaService.visualizarIsExecuting();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/visualizar/finished")
    public ResponseEntity<String> visualizarFinished() {
        String resultado = filaService.visualizarFinished();
        return ResponseEntity.ok(resultado);
    }

}
