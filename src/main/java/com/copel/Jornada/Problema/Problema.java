package com.copel.Jornada.Problema;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problema")
public class Problema {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String slug = UUID.randomUUID().toString();

    private String descricao;
    private int tempoEspera;
    private int tempoMedioAtendimento;

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
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

    public void setSlug(String slug) {
        this.slug = slug;
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
