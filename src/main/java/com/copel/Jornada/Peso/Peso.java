package com.copel.Jornada.Peso;

import com.copel.Jornada.Demanda.Demanda;

import jakarta.persistence.*;

@Entity
@Table(name = "peso")
public class Peso {
    
    public double calculoPesoDaDemanda(Demanda d) {
        int pesoPorCustoPecas = d.getPecas().definirPesoPorCustoPecas();
        int pesoPorCustoMaoObra = d.getMaoObra().definirPesoPorMaoObra();
        int pesoPorCustoEquipamento = d.getEquipamento().definirPesoPorEquipamento();
        int pesoPorRecorrencia = d.getRegiao().definirPesoPorRecorrencia();
        int pesoPorRegiao = d.getRegiao().definirPesoPorRegiao();
        int pesoPorConsumoEnergia = d.getConsumoEnergia().definirPesoPorConsumoEnergia();

        double valorDemandaSomada = pesoPorConsumoEnergia + pesoPorRegiao + pesoPorRecorrencia + pesoPorCustoPecas + pesoPorCustoMaoObra + pesoPorCustoEquipamento
                + d.getEntradaManual();

        d.setPesoFinal(valorDemandaSomada);
        return valorDemandaSomada;
    }
}
