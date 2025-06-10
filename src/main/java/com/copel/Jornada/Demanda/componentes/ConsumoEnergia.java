package com.copel.Jornada.Demanda.componentes;

public class ConsumoEnergia {
    
    private double consumoEnergia;

    public ConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    protected ConsumoEnergia() {
        
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }
}
