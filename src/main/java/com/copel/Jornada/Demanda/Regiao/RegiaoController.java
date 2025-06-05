package com.copel.Jornada.Demanda.Regiao;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regioes")
public class RegiaoController {
    
    private final RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping
    public List<Regiao> listarRegioes() {
        return regiaoService.listarTodas();
    }
}

