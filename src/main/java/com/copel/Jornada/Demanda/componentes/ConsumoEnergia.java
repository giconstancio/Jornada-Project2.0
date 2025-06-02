package com.copel.Jornada.Demanda.componentes;

public class ConsumoEnergia {
    
    private double consumoEnergia;

    public ConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public int definirPesoPorConsumoEnergia() {
        int pesoConsumoEnergia = (int) (consumoEnergia / 6);
        return pesoConsumoEnergia * 5;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }
}
