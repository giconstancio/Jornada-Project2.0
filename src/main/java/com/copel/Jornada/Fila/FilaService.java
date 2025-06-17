package com.copel.Jornada.Fila;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Util.FilaStatus;

import com.copel.Jornada.Demanda.DemandaRepository;

@Service
public class FilaService {
    
    private final Fila fila;
    private final DemandaRepository demandaRepository;

    @Autowired
    public FilaService(Fila fila, DemandaRepository demandaRepository) {
        this.fila = fila;
        this.demandaRepository = demandaRepository;
    }
    
    public void adicionarNovaDemanda(Demanda demanda) {
        fila.getTodasDemandas().add(demanda);
        fila.getDemandasSemFinalizadas().add(demanda);
    }

    public void redistribuirFilas() {
        List<Demanda> fila1 = demandaRepository.findByFilaOrderByPesoDesc(FilaStatus.ON_HOLDING);
        List<Demanda> fila2 = demandaRepository.findByFilaOrderByPesoDesc(FilaStatus.ON_GOING);
        List<Demanda> fila3 = demandaRepository.findByFilaOrderByPesoDesc(FilaStatus.IS_EXECUTING);

        int maxFila2 = 5;
        int maxFila3 = 10;

        List<Demanda> candidatasFila2 = new ArrayList<>();
        candidatasFila2.addAll(fila1);
        candidatasFila2.addAll(fila2);
        candidatasFila2.sort(Comparator.comparingDouble(Demanda::getPeso).reversed());

        for (int i = 0; i < candidatasFila2.size(); i++) {
            Demanda d = candidatasFila2.get(i);
            d.setFila(i < maxFila2 ? FilaStatus.ON_GOING : FilaStatus.ON_HOLDING);
            demandaRepository.save(d);
        }

        List<Demanda> novasFila2 = demandaRepository.findByFilaOrderByPesoDesc(FilaStatus.ON_GOING);
        List<Demanda> candidatasFila3 = new ArrayList<>();
        candidatasFila3.addAll(fila3);
        candidatasFila3.addAll(novasFila2);
        candidatasFila3.sort(Comparator.comparingDouble(Demanda::getPeso).reversed());

        for (int i = 0; i < candidatasFila3.size(); i++) {
            Demanda d = candidatasFila3.get(i);
            d.setFila(i < maxFila3 ? FilaStatus.IS_EXECUTING : FilaStatus.ON_GOING);
            demandaRepository.save(d);
        }
    }

    public ResponseEntity<String> finalizarDemanda(Long idDemanda) {
        Optional<Demanda> optionalDemanda = demandaRepository.findById(idDemanda);

        if (optionalDemanda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Demanda com ID " + idDemanda + " não encontrada.");
        }

        Demanda demanda = optionalDemanda.get();

        if (demanda.getFila() != FilaStatus.IS_EXECUTING) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A demanda deve estar em execução para ser finalizada.");
        }

        demanda.setFila(FilaStatus.FINISHED);
        demandaRepository.save(demanda);

        return ResponseEntity.ok("Demanda '" + demanda.getNome() + "' finalizada com sucesso.");
    }

}
