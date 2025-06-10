package com.copel.Jornada.Problema;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProblemaService {

    private final ProblemaRepository problemaRepository;

    public ProblemaService(ProblemaRepository problemaRepository) {
        this.problemaRepository = problemaRepository;
    }

    public List<Problema> listarTodos() {
        return problemaRepository.findAll();
    }

    public Problema criar(Problema problema) {
        return problemaRepository.save(problema);
    }

    public void deletar(Long id) {
        problemaRepository.deleteById(id);
    }

    public Problema atualizar(Long id, Problema problemaAtualizado) {
        return problemaRepository.findById(id)
                .map(p -> {
                    p.setDescricao(problemaAtualizado.getDescricao());
                    p.setNome(problemaAtualizado.getNome());
                    p.setTempoEspera(problemaAtualizado.getTempoEspera());
                    p.setTempoMedioAtendimento(problemaAtualizado.getTempoMedioAtendimento());
                    return problemaRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Problema não encontrado"));
    }

    public Problema buscarPorId(Long id) {
        return problemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problema não encontrado"));
    }

}
