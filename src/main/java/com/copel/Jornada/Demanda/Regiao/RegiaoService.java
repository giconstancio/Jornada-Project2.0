package com.copel.Jornada.Demanda.Regiao;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegiaoService {
    
    private final RegiaoRepository regiaoRepository;

    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }
 
    public List<Regiao> listarTodas() {
        return regiaoRepository.findAll();
    }
}
