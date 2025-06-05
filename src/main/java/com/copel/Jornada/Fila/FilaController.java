package com.copel.Jornada.Fila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fila")
public class FilaController {
    
    @Autowired
    private FilaService filaService;

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

    @GetMapping("/verificar-fila")
    public ResponseEntity<String> verificarFilaDemanda(@RequestParam String nome) {
        String resultado = filaService.verificarFilaDemanda(nome);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/incrementar-tempo")
    public ResponseEntity<String> implementacaoTempo() {
        filaService.implementacaoTempo();
        return ResponseEntity.ok("Tempo de 30 minutos incrementado nas filas com sucesso.");
    }
}
