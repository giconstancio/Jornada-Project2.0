package com.copel.Jornada.Problema.tipos;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "expansao_rede")
public class ExpansaoRede extends Problema {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void definirAtributos(Demanda d) {
        d.getProblema().setDescricao("Projeto de ampliação da rede elétrica para novas áreas ou maior demanda");
        d.getProblema().setTempoEspera(24);
        d.getProblema().setTempoMedioAtendimento(72);
    }

    public Long getId() {
        return id;
    }
}
