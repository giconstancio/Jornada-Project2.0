package com.copel.Jornada.PesoCalculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Util.FilaStatus;
import com.copel.Jornada.Demanda.DemandaRepository;

import jakarta.transaction.Transactional;

@Component
public class PesoCalculator {

    private final DemandaRepository demandaRepository;

    public PesoCalculator(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    public double calcularPeso(Demanda d) {
        int pesoPorCustoPecas = definirPesoPorCustoPecas(d);
        int pesoPorCustoMaoObra = definirPesoPorMaoObra(d);
        int pesoPorCustoEquipamento = definirPesoPorEquipamento(d);
        int pesoPorRecorrencia = definirPesoPorRecorrencia(d);
        int pesoPorRegiao = definirPesoPorRegiao(d);
        int pesoPorConsumoEnergia = definirPesoPorConsumoEnergia(d);

        double pesoTotal = pesoPorCustoPecas + pesoPorCustoMaoObra + pesoPorCustoEquipamento
                + pesoPorRecorrencia + pesoPorRegiao + pesoPorConsumoEnergia;

        d.setPeso(pesoTotal);
        return pesoTotal;
    }

    @Transactional
    public void recalcularPesos() {
        List<Demanda> demandas = demandaRepository.findByFilaIn(List.of(
            FilaStatus.ON_HOLDING,
            FilaStatus.ON_GOING,
            FilaStatus.IS_EXECUTING
        ));

        LocalDateTime agora = LocalDateTime.now();

        for (Demanda d : demandas) {
            LocalDateTime ultimaAtualizacao = d.getUltimaAtualizacaoPeso();

            if (ultimaAtualizacao != null) {
                long segundosParado = Duration.between(ultimaAtualizacao, agora).toSeconds();

                if (segundosParado > 0) {
                    double aumento = segundosParado * (d.getCustoHoraParado() / 3600.0);
                    d.setPeso(d.getPeso() + aumento);
                }
            }

            d.setUltimaAtualizacaoPeso(agora);
        }

        demandaRepository.saveAll(demandas);
    }

    private int definirPesoPorCustoPecas(Demanda d) {
        return (int) (d.getPecas().getCustoPecas() / 1000);
    }

    private int definirPesoPorMaoObra(Demanda d) {
        return (int) (d.getMaoObra().getCustoMaoObra() / 1000) * 2;
    }

    private int definirPesoPorEquipamento(Demanda d) {
        int peso = new Random().nextInt(3) + 1;
        return (int) (d.getEquipamento().getCustoEquipamento() / 1000) * peso;
    }

    private int definirPesoPorConsumoEnergia(Demanda d) {
        return ((int) (d.getConsumoEnergia().getConsumoEnergia() / 6)) * 5;
    }

    private int definirPesoPorRegiao(Demanda d) {
        String nome = d.getRegiao().getNomeRegiao().toLowerCase();
        return switch (nome) {
            case "centro" -> 10;
            case "bairro" -> 20;
            case "rural" -> 30;
            default -> 0;
        };
    }

    private int definirPesoPorRecorrencia(Demanda d) {
        return d.getRegiao().getRecorrenciaChamados() / 10;
    }
}