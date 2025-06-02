package com.copel.Jornada.Demanda.componentes;

import java.util.Random;

public class Equipamento {
    
    private double custoEquipamento;

    public Equipamento(double custoEquipamento) {
        this.custoEquipamento = custoEquipamento;
    }

    public int definirPesoPorEquipamento() {
        Random random = new Random();

        int peso = random.nextInt(3) + 1;
        int pesoEquipamento = (int) (custoEquipamento / 1000);
        return pesoEquipamento * peso;
    }

    public double getCustoEquipamento() {
        return custoEquipamento;
    }

    public void setCustoEquipamento(double custoEquipamento) {
        this.custoEquipamento = custoEquipamento;
    }
}
