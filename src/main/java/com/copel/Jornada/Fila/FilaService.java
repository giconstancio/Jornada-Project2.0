package com.copel.Jornada.Fila;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
   
    public String visualizarOnHolding() {
        String resultado = "EM ESPERA - ";

        if (fila.getFilaOnHolding().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.getFilaOnHolding()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarOnGoing() {
        String resultado = "EM ANDAMENTO - ";

        if (fila.getFilaOnGoing().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.getFilaOnGoing()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarIsExecuting() {
        String resultado = "SENDO EXECUTADA - ";

        if (fila.getFilaIsExecuting().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.getFilaIsExecuting()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarFinished() {
        String resultado = "FINALIZADA - ";

        if (fila.getFilaFinished().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.getFilaFinished()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
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

    public String finalizarDemanda(Long idDemanda) {
        return demandaRepository.findById(idDemanda)
            .map(demanda -> {
                if (demanda.getFila() != FilaStatus.IS_EXECUTING) {
                    return "A demanda deve estar em execução para ser finalizada.";
                }
                demanda.setFila(FilaStatus.FINISHED);
                demandaRepository.save(demanda);
                return "Demanda '" + demanda.getNome() + "' finalizada com sucesso.";
            })
            .orElse("Demanda com ID " + idDemanda + " não encontrada.");
    }

}
