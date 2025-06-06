package com.copel.Jornada.Fila;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Demanda.DemandaRepository;
import com.copel.Jornada.Util.FilaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Component
public class Fila {

    private final Map<Integer, List<Demanda>> filas = new HashMap<>();
    private final List<Demanda> listaDeDemandas = new ArrayList<>();
    private final List<Demanda> listaDeDemandasSemFinalizadas = new ArrayList<>();

    @Autowired
    private DemandaRepository demandaRepository;

    public Fila() {
        filas.put(FilaStatus.ON_HOLDING, new ArrayList<>());
        filas.put(FilaStatus.ON_GOING, new ArrayList<>());
        filas.put(FilaStatus.IS_EXECUTING, new ArrayList<>());
        filas.put(FilaStatus.FINISHED, new ArrayList<>());
    }

    @PostConstruct
    public void inicializarFila() {
        List<Demanda> todasDemandas = demandaRepository.findAll();
        listaDeDemandas.addAll(todasDemandas);

        for (Demanda d : todasDemandas) {
            if (d.getFila() != FilaStatus.FINISHED) {
                listaDeDemandasSemFinalizadas.add(d);
            }
        }

        atualizarFilas();
    }

    public void adicionarNovaDemanda(Demanda demanda) {
        listaDeDemandas.add(demanda);
        listaDeDemandasSemFinalizadas.add(demanda);
        atualizarFilas();
    }

    public void atualizarFilas() {
        limparFilasAtivas();
        ordenarDemandasPorPesoBubbleSort(listaDeDemandasSemFinalizadas);
        distribuirDemandasNasFilas();
    }

    private void limparFilasAtivas() {
        filas.get(FilaStatus.ON_HOLDING).clear();
        filas.get(FilaStatus.ON_GOING).clear();
        filas.get(FilaStatus.IS_EXECUTING).clear();
    }

    private void ordenarDemandasPorPesoBubbleSort(List<Demanda> demandas) {
        boolean troca;
        do {
            troca = false;
            for (int i = 0; i < demandas.size() - 1; i++) {
                Demanda atual = demandas.get(i);
                Demanda proxima = demandas.get(i + 1);
                if (atual.getPeso() < proxima.getPeso()) {
                    demandas.set(i, proxima);
                    demandas.set(i + 1, atual);
                    troca = true;
                }
            }
        } while (troca);
    }

    private void distribuirDemandasNasFilas() {
        int maxExecutando = 5;
        int maxAndamento = 10;

        for (int i = 0; i < listaDeDemandasSemFinalizadas.size(); i++) {
            Demanda demanda = listaDeDemandasSemFinalizadas.get(i);

            if (i < maxExecutando) {
                moverParaFila(demanda, FilaStatus.IS_EXECUTING);
            } else if (i < maxAndamento) {
                moverParaFila(demanda, FilaStatus.ON_GOING);
            } else {
                moverParaFila(demanda, FilaStatus.ON_HOLDING);
            }
        }
    }

    private void moverParaFila(Demanda demanda, int statusFila) {
        demanda.setFila(statusFila);
        demandaRepository.save(demanda);
        filas.get(statusFila).add(demanda);
    }

    public String verificarFila(Demanda d) {
        for (Map.Entry<Integer, List<Demanda>> entry : filas.entrySet()) {
            if (entry.getValue().contains(d)) {
                switch (entry.getKey()) {
                    case FilaStatus.ON_HOLDING: return "A demanda está na fila de espera (onHolding).";
                    case FilaStatus.ON_GOING: return "A demanda está na fila em andamento (onGoing).";
                    case FilaStatus.IS_EXECUTING: return "A demanda está sendo executada (isExecuting).";
                    case FilaStatus.FINISHED: return "A demanda foi finalizada (finished).";
                }
            }
        }
        return "A demanda não foi encontrada em nenhuma fila.";
    }

    public List<Demanda> getFilaOnHolding() {
        return Collections.unmodifiableList(filas.get(FilaStatus.ON_HOLDING));
    }

    public List<Demanda> getFilaOnGoing() {
        return Collections.unmodifiableList(filas.get(FilaStatus.ON_GOING));
    }

    public List<Demanda> getFilaIsExecuting() {
        return Collections.unmodifiableList(filas.get(FilaStatus.IS_EXECUTING));
    }

    public List<Demanda> getFilaFinished() {
        return Collections.unmodifiableList(filas.get(FilaStatus.FINISHED));
    }

    public List<Demanda> getTodasDemandas() {
        return Collections.unmodifiableList(listaDeDemandas);
    }

    public List<Demanda> getDemandasSemFinalizadas() {
        return Collections.unmodifiableList(listaDeDemandasSemFinalizadas);
    }
}
