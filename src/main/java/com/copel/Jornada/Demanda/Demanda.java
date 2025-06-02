package com.copel.Jornada.Demanda;

import org.springframework.security.access.event.PublicInvocationEvent;

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

    @Embedded
    private Regiao regiao;

    @Embedded
    private ConsumoEnergia consumoEnergia;

    private int entradaManual;
    private double pesoFinal;
    private String status;


    public Demanda(String nome, Problema classeProblema, double distanciaSede, double distanciaVeiculo,
            double custoPecas, double custoMaoObra, double custoEquipamento, String regiao, double consumoEnergia, String status) {
        this.nome = nome;
        this.problema = classeProblema;
        this.distanciaSede = distanciaSede;
        this.distanciaVeiculo = distanciaVeiculo;
        this.custoHoraParado = 0.0;
        this.pecas = new Pecas(custoPecas);
        this.maoObra = new MaoObra(custoMaoObra);
        this.equipamento = new Equipamento(custoEquipamento);
        this.regiao = new Regiao(regiao);
        this.consumoEnergia = new ConsumoEnergia(consumoEnergia);
        this.pesoFinal = 0.0;
        this.status = status;
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

    public double getcustoHoraParado() {
        return custoHoraParado;
    }

    public void setcustoHoraParado(double custoHoraParado) {
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

    public int getEntradaManual() {
        return entradaManual;
    }

    public void setEntradaManual(int entradaManual) {
        this.entradaManual = entradaManual;
    }

    public double getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(double pesoFinalDaDemanda) {
        this.pesoFinal = pesoFinalDaDemanda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
