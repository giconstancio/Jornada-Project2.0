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

        if (fila.get_onHolding().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.get_onHolding()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarOnGoing() {
        String resultado = "EM ANDAMENTO - ";

        if (fila.get_onGoing().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.get_onGoing()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarIsExecuting() {
        String resultado = "SENDO EXECUTADA - ";

        if (fila.get_isExecuting().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.get_isExecuting()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String visualizarFinished() {
        String resultado = "FINALIZADA - ";

        if (fila.get_finished().isEmpty()) {
            String visualizar = "Não há nenhuma demanda nessa fila";
            resultado += visualizar;
            return resultado;
        } else {
            for (Demanda d : fila.get_finished()) {
                String visualizar = String.format("Nome: %s\nID: %s\nClasse de problema: %s\nDescrição: %s\nDistância da sede: %.2f km\nDistância do veículo: %.2f km\nPeso da Demanda: %.0f\n\n", d.getNome(), d.getProblema().getId(), d.getProblema(), d.getProblema().getDescricao(),
                        d.getDistanciaSede(), d.getDistanciaVeiculo(), d.getPeso());
                resultado += visualizar;
            }
            return resultado;
        }
    }

    public String verificarFilaDemanda(String nome) {
        Demanda d = demandaService.retornarDemandaPeloNome(nome);
        if (d != null) {
            return fila.verificarFila(d);
        }
        return "Erro: Demanda não encontrada.";
    }

    public void implementacaoTempo() {
        for (Demanda d : fila.get_onHolding()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }

        for (Demanda d : fila.get_isExecuting()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }

        for (Demanda d : fila.get_onGoing()) {
            double aumentoHoraParado = d.getCustoHoraParado() + 0.5;
            d.setPeso(d.getPeso() + aumentoHoraParado);
        }
    }
}
