package com.copel.Jornada.Demanda.Regiao;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regioes")
public class RegiaoController {
    
    private final RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping("/getAll")
    public List<Regiao> listarRegioes() {
        return regiaoService.listarTodas();
    }


    @GetMapping("/getById/{id}")
    public Regiao buscarPorId(@PathVariable Long id) {
        return regiaoService.buscarPorId(id);
    }

    @PostMapping("/create")
    public Regiao criar(@RequestBody Regiao regiao) {
        return regiaoService.criar(regiao);
    }

    @PutMapping("/updateRegiao/{id}")
    public Regiao atualizar(@PathVariable Long id, @RequestBody Regiao regiao) {
        return regiaoService.atualizar(id, regiao);
    }

    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Long id) {
        regiaoService.deletar(id);
    }
}

