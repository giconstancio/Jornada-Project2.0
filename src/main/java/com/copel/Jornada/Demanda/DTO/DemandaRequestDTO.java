package com.copel.Jornada.Demanda.DTO;

public class DemandaRequestDTO {

    private String nome;
    private Long problemaId;
    private double distanciaSede;
    private double distanciaVeiculo;
    private double custoHoraParado;
    private double custoPecas;
    private double custoMaoObra;
    private double custoEquipamento;
    private double consumoEnergia;
    private Long regiaoId;
    private int fila;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getProblemaId() {
        return problemaId;
    }

    public void setProblemaId(Long problemaId) {
        this.problemaId = problemaId;
    }

    public double getDistanciaSede() {
        return distanciaSede;
    }

    public void setDistanciaSede(double distanciaSede) {
        this.distanciaSede = distanciaSede;
    }

    public double getDistanciaVeiculo() {
        return distanciaVeiculo;
    }

    public void setDistanciaVeiculo(double distanciaVeiculo) {
        this.distanciaVeiculo = distanciaVeiculo;
    }

    public double getCustoHoraParado() {
        return custoHoraParado;
    }

    public void setCustoHoraParado(double custoHoraParado) {
        this.custoHoraParado = custoHoraParado;
    }

    public double getCustoPecas() {
        return custoPecas;
    }

    public void setCustoPecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }

    public double getCustoMaoObra() {
        return custoMaoObra;
    }

    public void setCustoMaoObra(double custoMaoObra) {
        this.custoMaoObra = custoMaoObra;
    }

    public double getCustoEquipamento() {
        return custoEquipamento;
    }

    public void setCustoEquipamento(double custoEquipamento) {
        this.custoEquipamento = custoEquipamento;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public Long getRegiaoId() {
        return regiaoId;
    }

    public void setRegiaoId(Long regiaoId) {
        this.regiaoId = regiaoId;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
}


