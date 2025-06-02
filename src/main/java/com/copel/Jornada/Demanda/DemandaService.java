package com.copel.Jornada.Demanda;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.copel.Jornada.Fila.Fila;

@Service
public class DemandaService {

    private final Fila fila;

    public DemandaService(Fila fila) {
        this.fila = fila;
    }
    
    public Demanda adicionarDemanda(Demanda d) {
        fila.getListaDeDemandas().add(d);
        fila.getListaDeDemandasSemFinalizadas().add(d);
        return d;
    }

    public Demanda retornarDemandaPeloNome(String nome) {
        for (Demanda d : fila.getListaDeDemandas()) {
            if (d.getNome().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        return null;
    }

    public String alterarStatusParaFinalizado(Demanda d) {
        ArrayList<Demanda> filaAnterior = fila.retornarFilaPelaDemanda(d);
        Demanda demandaParaRemover = null;

        for (Demanda demanda : filaAnterior) {
            if (demanda.getProblema().getId().equals(d.getProblema().getId())) {
                demandaParaRemover = demanda;
                break;
            }
        }

        if (demandaParaRemover != null) {
            filaAnterior.remove(demandaParaRemover);
        }

        fila.get_finished().add(d);
        return "Demanda finalizada!";
    }
}
