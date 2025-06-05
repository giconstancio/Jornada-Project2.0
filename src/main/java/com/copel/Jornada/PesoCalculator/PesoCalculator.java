package com.copel.Jornada.PesoCalculator;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.copel.Jornada.Demanda.Demanda;

@Component
public class PesoCalculator {

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