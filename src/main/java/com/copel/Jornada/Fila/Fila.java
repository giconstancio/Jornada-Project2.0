package com.copel.Jornada.Fila;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Util.FilaStatus;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Fila {

    private final Map<Integer, List<Demanda>> filas = new HashMap<>();
    private final List<Demanda> listaDeDemandas = new ArrayList<>();
    private final List<Demanda> listaDeDemandasSemFinalizadas = new ArrayList<>();

    public Fila() {
        filas.put(FilaStatus.ON_HOLDING, new ArrayList<>());
        filas.put(FilaStatus.ON_GOING, new ArrayList<>());
        filas.put(FilaStatus.IS_EXECUTING, new ArrayList<>());
        filas.put(FilaStatus.FINISHED, new ArrayList<>());
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
