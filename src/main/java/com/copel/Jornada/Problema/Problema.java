package com.copel.Jornada.Problema;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@Table(name = "problema")
public class Problema {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private int tempoEspera;
    private int tempoMedioAtendimento;

    public Problema(Long id, String nome, String descricao, int tempoEspera, int tempoMedioAtendimento) {
    this.nome = nome;
    this.id = id;
    this.descricao = descricao;
    this.tempoEspera = tempoEspera;
    this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public int getTempoMedioAtendimento() {
        return tempoMedioAtendimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public void setTempoMedioAtendimento(int tempoMedioAtendimento) {
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }
}
