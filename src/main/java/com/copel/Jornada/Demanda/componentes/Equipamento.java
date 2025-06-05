package com.copel.Jornada.Demanda.componentes;

public class Equipamento {
    
    private double custoEquipamento;

    public Equipamento(double custoEquipamento) {
        this.custoEquipamento = custoEquipamento;
    }

    protected Equipamento() {
        
    }

    public double getCustoEquipamento() {
        return custoEquipamento;
    }

    public void setCustoEquipamento(double custoEquipamento) {
        this.custoEquipamento = custoEquipamento;
    }
}
