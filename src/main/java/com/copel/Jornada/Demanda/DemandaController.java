package com.copel.Jornada.Demanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.copel.Jornada.Demanda.DTO.DemandaRequestDTO;
import com.copel.Jornada.Demanda.Regiao.Regiao;

import java.util.List;

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

    /* 
    @PutMapping("/editar-demanda/{id}")
    public ResponseEntity<Demanda> editarDemanda(@PathVariable Long id, @RequestBody Demanda novaDemanda) {
        Demanda atualizada = demandaService.editarDemanda(id, novaDemanda);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-demanda/{id}")
    public ResponseEntity<Void> deletarDemanda(@PathVariable Long id) {
        boolean removida = demandaService.deletarDemanda(id);
        if (removida) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/
    @GetMapping("/getAll")
    public List<Demanda> listarDemandas() {
        return demandaService.listarTodas();
    }
    
    /* FINALIZAR ROTA
    @GetMapping("/buscarPor/{nome}")
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
    } */
}
