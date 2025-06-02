package com.copel.Jornada.Demanda.componentes;

public class MaoObra {
    
    private double custoMaoObra;

    public MaoObra(double custoMaoObra) {
        this.custoMaoObra = custoMaoObra;
    }

    public int definirPesoPorMaoObra() {
        int pesoMaoObra = (int) (custoMaoObra / 1000);
        return pesoMaoObra * 2;
    }

    public double getCustoMaoObra() {
        return custoMaoObra;
    }

    public void setCustoPecas(double custoMaoObra) {
        this.custoMaoObra = custoMaoObra;
    }
}

