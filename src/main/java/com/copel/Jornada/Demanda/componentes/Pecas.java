package com.copel.Jornada.Demanda.componentes;

public class Pecas {
    
    private double custoPecas;

    public Pecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }

    protected Pecas() {
        
    }

    public double getCustoPecas() {
        return custoPecas;
    }

    public void setCustoPecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }
}
