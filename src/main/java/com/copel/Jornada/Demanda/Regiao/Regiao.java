package com.copel.Jornada.Demanda.Regiao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "regiao")
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRegiao;
    private int recorrenciaChamados;


    public Regiao() {
    }

        public Long getRegiaoId() {
        return id;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public int getRecorrenciaChamados() {
        return recorrenciaChamados;
    }

    public void setRecorrenciaChamados(int recorrenciaChamados) {
        this.recorrenciaChamados = recorrenciaChamados;
    }
}
