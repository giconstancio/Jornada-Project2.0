package com.Seeders;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import com.copel.Jornada.Problema.Problema;
import com.copel.Jornada.Problema.ProblemaRepository;

@Component
public class ProblemaSeedCommand {
    
    private final ProblemaRepository problemaRepository;

    public ProblemaSeedCommand(ProblemaRepository problemaRepository) {
        this.problemaRepository = problemaRepository;
    }

    @Transactional
    public void seedProblemas() {
        if (problemaRepository.count() > 0) return;

        List<Problema> problemas = List.of(
            new Problema(null, "Falta de energia", "Falta de energia em uma ou mais residências/regiões", 1, 2),
            new Problema(null, "Falha técnica", "Equipamento da rede com falha técnica (transformador, poste, etc)", 2, 4),
            new Problema(null, "Atendimento ao cliente", "Solicitação de vistoria, mudança de titularidade, ligação nova etc", 2, 3),
            new Problema(null, "Manutenção preventiva", "Intervenção programada para evitar falhas na rede elétrica", 4, 6),
            new Problema(null, "Expansão de rede", "Projeto de ampliação da rede elétrica para novas áreas ou maior demanda", 24, 72)
        );

        problemaRepository.saveAll(problemas);

        System.out.println("✅ Problemas base cadastrados com sucesso.");
    }
}
