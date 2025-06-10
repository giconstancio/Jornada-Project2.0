package com.copel.Jornada.Demanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.copel.Jornada.Demanda.DTO.DemandaRequestDTO;
import com.copel.Jornada.Demanda.DTO.DemandaUpdateDTO;

@RestController
@RequestMapping("/api/demandas")
public class DemandaController {
    
    private final DemandaService demandaService;

    @Autowired
    public DemandaController(DemandaService demandaService) {
        this.demandaService = demandaService;
    }
    
    @PostMapping("/criar-demanda")
    public ResponseEntity<Demanda> criarDemanda(@RequestBody DemandaRequestDTO dto) {
        Demanda demanda = demandaService.adicionarDemanda(dto);
        return ResponseEntity.ok(demanda);
    } 

    @PutMapping("/editarDemanda/{id}")
    public ResponseEntity<Demanda> editarDemanda(@PathVariable Long id, @RequestBody DemandaUpdateDTO dtoUpdate) {
        Demanda atualizada = demandaService.editarDemanda(id, dtoUpdate);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarDemanda/{id}")
    public void deletarDemanda(@PathVariable Long id) {
        demandaService.deletarDemanda(id);
    }

    @GetMapping("/getAll")
    public List<Demanda> listarDemandas() {
        return demandaService.listarTodas();
    }
    
    @GetMapping("/buscarPorId/{id}")
    public Demanda buscarPorId(@PathVariable Long id) {
        return demandaService.buscarPorId(id);
    }

    /*@PutMapping("/finalizar")
    public ResponseEntity<String> finalizarDemanda(@RequestBody Demanda demanda) {
        String resposta = demandaService.alterarStatusParaFinalizado(demanda);
        return ResponseEntity.ok(resposta);
    } */
}
