package com.copel.Jornada.Seeders;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.copel.Jornada.Demanda.Regiao.Regiao;
import com.copel.Jornada.Demanda.Regiao.RegiaoRepository;

@Component
public class RegiaoSeedCommand {

    private final RegiaoRepository regiaoRepository;

    public RegiaoSeedCommand(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    @Transactional
    public void seedRegiao() {
        if (regiaoRepository.count() == 0) {

            Regiao centro = new Regiao();
            centro.setNomeRegiao("centro");
            centro.setRecorrenciaChamados(15);
            regiaoRepository.save(centro);

            Regiao bairro = new Regiao();
            bairro.setNomeRegiao("bairro");
            bairro.setRecorrenciaChamados(25);
            regiaoRepository.save(bairro);

            Regiao rural = new Regiao();
            rural.setNomeRegiao("rural");
            rural.setRecorrenciaChamados(10);
            regiaoRepository.save(rural);

            System.out.println("Seed de regiões concluído.");
        }
    }
}