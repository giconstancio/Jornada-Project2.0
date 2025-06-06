package com.copel.Jornada.Demanda.Regiao;

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

    public Regiao criar(Regiao regiao) {
        return regiaoRepository.save(regiao);
    }

    public void deletar(Long id) {
        regiaoRepository.deleteById(id);
    }

    public Regiao atualizar(Long id, Regiao regiaoAtualizada) {
        return regiaoRepository.findById(id)
                .map(r -> {
                    r.setNomeRegiao(regiaoAtualizada.getNomeRegiao());
                    r.setRecorrenciaChamados(regiaoAtualizada.getRecorrenciaChamados());
                    return regiaoRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("Região não encontrado"));
    }

    public Regiao buscarPorId(Long id) {
        return regiaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrado"));
    }
}
