package com.copel.Jornada.Demanda.componentes;

public class MaoObra {
    
    private double custoMaoObra;

    public MaoObra(double custoMaoObra) {
        this.custoMaoObra = custoMaoObra;
    }

    protected MaoObra() {
        
    }

    public double getCustoMaoObra() {
        return custoMaoObra;
    }

    public void setCustoPecas(double custoMaoObra) {
        this.custoMaoObra = custoMaoObra;
    }
}

