package com.copel.Jornada.Fila;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.copel.Jornada.Demanda.Demanda;

import jakarta.persistence.*;

@Entity
@Component
@Table(name = "fila")
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ArrayList<Demanda> listaDeDemandas = new ArrayList<>();
    private ArrayList<Demanda> listaDeDemandasSemFinalizadas = new ArrayList<>();
    private ArrayList<Demanda> on_going = new ArrayList<>();
    private ArrayList<Demanda> on_holding = new ArrayList<>();
    private ArrayList<Demanda> is_executing = new ArrayList<>();
    private ArrayList<Demanda> finished = new ArrayList<>();

    public Fila() {
    }

    public void filaViva() {

        int tamanhoMax = 5;

        on_going.clear();
        on_holding.clear();
        is_executing.clear();

        boolean troca;
        do {
            troca = false;
            for (int a = 0; a < listaDeDemandasSemFinalizadas.size() - 1; a++) {
                if (listaDeDemandasSemFinalizadas.get(a) != null && listaDeDemandasSemFinalizadas.get(a + 1) != null) {
                    if (listaDeDemandasSemFinalizadas.get(a).getPesoFinal() < listaDeDemandasSemFinalizadas.get(a + 1).getPesoFinal()) {
                        Demanda aux = listaDeDemandasSemFinalizadas.get(a);
                        listaDeDemandasSemFinalizadas.set(a, listaDeDemandasSemFinalizadas.get(a + 1));
                        listaDeDemandasSemFinalizadas.set(a + 1, aux);
                        troca = true;
                    }
                }
            }
        } while (troca);

        for (int a = 0; a < listaDeDemandasSemFinalizadas.size(); a++) {

            if (a < tamanhoMax) {

                Demanda d = listaDeDemandasSemFinalizadas.get(a);
                is_executing.add(d);

            } else if (a < 10) {

                Demanda d = listaDeDemandasSemFinalizadas.get(a);
                on_going.add(d);

            } else {

                Demanda d = listaDeDemandasSemFinalizadas.get(a);
                on_holding.add(d);

            }

        }
    }

    public String verificarFila(Demanda d) {
        if (on_holding.contains(d)) {
            return "A demanda está na fila de espera (onHolding).";
        } else if (on_going.contains(d)) {
            return "A demanda está na fila em andamento (onGoing).";
        } else if (is_executing.contains(d)) {
            return "A demanda está sendo executada (isExecuting).";
        } else if (finished.contains(d)) {
            return "A demanda foi finalizada (finished).";
        }
        return "A demanda não foi encontrada em nenhuma fila.";
    }

    public ArrayList<Demanda> retornarFilaPelaDemanda(Demanda d) {
        if (on_holding.contains(d)) {
            return get_onHolding();
        } else if (on_going.contains(d)) {
            return get_onGoing();
        } else if (is_executing.contains(d)) {
            return get_isExecuting();
        } else if (finished.contains(d)) {
            return get_finished();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Demanda> get_onHolding() {
        return on_holding;
    }

    public ArrayList<Demanda> get_onGoing() {
        return on_going;
    }

    public ArrayList<Demanda> get_isExecuting() {
        return is_executing;
    }

    public ArrayList<Demanda> get_finished() {
        return finished;
    }

    public ArrayList<Demanda> getListaDeDemandas() {
        return listaDeDemandas;
    }

    public ArrayList<Demanda> getListaDeDemandasSemFinalizadas() {
        return listaDeDemandasSemFinalizadas;
    }

    public void setListaDeDemandasSemFinalizadas(ArrayList<Demanda> listaDeDemandasSemFinalizadas) {
        this.listaDeDemandasSemFinalizadas = listaDeDemandasSemFinalizadas;
    }
}
