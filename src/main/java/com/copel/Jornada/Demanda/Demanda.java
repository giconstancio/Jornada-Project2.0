package com.copel.Jornada.Demanda;

import java.time.LocalDateTime;

import com.copel.Jornada.Demanda.Regiao.Regiao;
import com.copel.Jornada.Demanda.componentes.*;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "demanda")
public class Demanda {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "problema_id")
    private Problema problema;
    
    private double distanciaSede;
    private double distanciaVeiculo;
    private double custoHoraParado;

    @Embedded
    private Pecas pecas;

    @Embedded
    private MaoObra maoObra;

    @Embedded
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;

    @Embedded
    private ConsumoEnergia consumoEnergia;

    private double peso;

    private int fila;

    @Column(name = "ultima_atualizacao_peso")
    private LocalDateTime ultimaAtualizacaoPeso;


    public Demanda(String nome, Problema classeProblema, double distanciaSede, double distanciaVeiculo,
            double custoPecas, double custoMaoObra, double custoEquipamento, double consumoEnergia, int filaAtual) {
        this.nome = nome;
        this.problema = classeProblema;
        this.distanciaSede = distanciaSede;
        this.distanciaVeiculo = distanciaVeiculo;
        this.custoHoraParado = 0.0;
        this.pecas = new Pecas(custoPecas);
        this.maoObra = new MaoObra(custoMaoObra);
        this.equipamento = new Equipamento(custoEquipamento);
        this.regiao = null;
        this.consumoEnergia = new ConsumoEnergia(consumoEnergia);
        this.peso = 0.0;
        this.fila = filaAtual;
    }

    public Demanda() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
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

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public MaoObra getMaoObra() {
        return maoObra;
    }

    public void setMaoObra(MaoObra maoObra) {
        this.maoObra = maoObra;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public ConsumoEnergia getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(ConsumoEnergia consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double pesoFinalDaDemanda) {
        this.peso = pesoFinalDaDemanda;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int filaAtual) {
        this.fila = filaAtual;
    }

    public LocalDateTime getUltimaAtualizacaoPeso() {
        return ultimaAtualizacaoPeso;
    }

    public void setUltimaAtualizacaoPeso(LocalDateTime data) {
        this.ultimaAtualizacaoPeso = data;
    }
}
