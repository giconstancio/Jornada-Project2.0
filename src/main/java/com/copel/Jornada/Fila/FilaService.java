package com.copel.Jornada.Fila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Demanda.DemandaService;

@Service
public class FilaService {
    
    private final Fila fila;
    private final DemandaService demandaService;

    @Autowired
    public FilaService(Fila fila, DemandaService demandaService) {
        this.fila = fila;
        this.demandaService = demandaService;
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

    /* VERIFICAR NECESSIDADE
    public String verificarFilaDemanda(String nome) {
        Demanda d = demandaService.retornarDemandaPeloNome(nome);
        if (d != null) {
            return fila.verificarFila(d);
        }
        return "Erro: Demanda não encontrada.";
    }
     */

    public void implementacaoTempo() {
        for (Demanda d : fila.getFilaOnHolding()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }

        for (Demanda d : fila.getFilaIsExecuting()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }

        for (Demanda d : fila.getFilaOnGoing()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }
    }
}
