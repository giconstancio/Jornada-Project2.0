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
}
