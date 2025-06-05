package com.copel.Jornada.Fila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Util.FilaStatus;

@Component
public class Fila {

    private final Map<Integer, List<Demanda>> filas = new HashMap<>();

    private List<Demanda> listaDeDemandas = new ArrayList<>();
    private List<Demanda> listaDeDemandasSemFinalizadas = new ArrayList<>();

    public Fila() {
        filas.put(FilaStatus.ON_HOLDING, new ArrayList<>());
        filas.put(FilaStatus.ON_GOING, new ArrayList<>());
        filas.put(FilaStatus.IS_EXECUTING, new ArrayList<>());
        filas.put(FilaStatus.FINISHED, new ArrayList<>());
    }

    public void filaViva() {
        int tamanhoMax = 5;

        filas.get(FilaStatus.ON_HOLDING).clear();
        filas.get(FilaStatus.ON_GOING).clear();
        filas.get(FilaStatus.IS_EXECUTING).clear();

        boolean troca;
        do {
            troca = false;
            for (int a = 0; a < listaDeDemandasSemFinalizadas.size() - 1; a++) {
                Demanda atual = listaDeDemandasSemFinalizadas.get(a);
                Demanda proxima = listaDeDemandasSemFinalizadas.get(a + 1);

                if (atual != null && proxima != null) {
                    if (atual.getPeso() < proxima.getPeso()) {
                        listaDeDemandasSemFinalizadas.set(a, proxima);
                        listaDeDemandasSemFinalizadas.set(a + 1, atual);
                        troca = true;
                    }
                }
            }
        } while (troca);

        for (int i = 0; i < listaDeDemandasSemFinalizadas.size(); i++) {
            Demanda d = listaDeDemandasSemFinalizadas.get(i);

            if (i < tamanhoMax) {
                filas.get(FilaStatus.IS_EXECUTING).add(d);
            } else if (i < 10) {
                filas.get(FilaStatus.ON_GOING).add(d);
            } else {
                filas.get(FilaStatus.ON_HOLDING).add(d);
            }
        }
    }

    public String verificarFila(Demanda d) {
        for (Map.Entry<Integer, List<Demanda>> entry : filas.entrySet()) {
            if (entry.getValue().contains(d)) {
                switch (entry.getKey()) {
                    case FilaStatus.ON_HOLDING:
                        return "A demanda está na fila de espera (onHolding).";
                    case FilaStatus.ON_GOING:
                        return "A demanda está na fila em andamento (onGoing).";
                    case FilaStatus.IS_EXECUTING:
                        return "A demanda está sendo executada (isExecuting).";
                    case FilaStatus.FINISHED:
                        return "A demanda foi finalizada (finished).";
                }
            }
        }
        return "A demanda não foi encontrada em nenhuma fila.";
    }

    public List<Demanda> retornarFilaPelaDemanda(Demanda d) {
        for (List<Demanda> fila : filas.values()) {
            if (fila.contains(d)) {
                return fila;
            }
        }
        return null;
    }

    public List<Demanda> get_onHolding() {
        return filas.get(FilaStatus.ON_HOLDING);
    }

    public List<Demanda> get_onGoing() {
        return filas.get(FilaStatus.ON_GOING);
    }

    public List<Demanda> get_isExecuting() {
        return filas.get(FilaStatus.IS_EXECUTING);
    }

    public List<Demanda> get_finished() {
        return filas.get(FilaStatus.FINISHED);
    }

    public List<Demanda> getListaDeDemandas() {
        return listaDeDemandas;
    }

    public List<Demanda> getListaDeDemandasSemFinalizadas() {
        return listaDeDemandasSemFinalizadas;
    }

    public void setListaDeDemandasSemFinalizadas(List<Demanda> lista) {
        this.listaDeDemandasSemFinalizadas = lista;
    }
}