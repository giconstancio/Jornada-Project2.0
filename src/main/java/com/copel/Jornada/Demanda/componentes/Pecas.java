package com.copel.Jornada.Demanda.componentes;

public class Pecas {
    
    private double custoPecas;

    public Pecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }

    public int definirPesoPorCustoPecas() {
        int pesoPecas = (int) (custoPecas / 1000);
        return pesoPecas;
    }

    public double getCustoPecas() {
        return custoPecas;
    }

    public void setCustoPecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }
}
