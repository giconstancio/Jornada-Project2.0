package com.copel.Jornada.Demanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demandas")
public class DemandaController {
    
    private final DemandaService demandaService;

    @Autowired
    public DemandaController(DemandaService demandaService) {
        this.demandaService = demandaService;
    }

    @PostMapping
    public ResponseEntity<Demanda> criarDemanda(@RequestBody Demanda demanda) {
        Demanda demandaCriada = demandaService.adicionarDemanda(demanda);
        return ResponseEntity.ok(demandaCriada);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Demanda> buscarPorNome(@RequestParam String nome) {
        Demanda d = demandaService.retornarDemandaPeloNome(nome);
        if (d != null) {
            return ResponseEntity.ok(d);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/finalizar")
    public ResponseEntity<String> finalizarDemanda(@RequestBody Demanda demanda) {
        String resposta = demandaService.alterarStatusParaFinalizado(demanda);
        return ResponseEntity.ok(resposta);
    }
}
