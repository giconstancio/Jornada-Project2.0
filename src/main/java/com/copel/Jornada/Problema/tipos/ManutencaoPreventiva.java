package com.copel.Jornada.Problema.tipos;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "manutencao_preventiva")
public class ManutencaoPreventiva extends Problema {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void definirAtributos(Demanda d) {
        d.getProblema().setDescricao("Intervenção programada para evitar falhas na rede elétrica");
        d.getProblema().setTempoEspera(4);
        d.getProblema().setTempoMedioAtendimento(6);
    }

    public Long getId() {
        return id;
    }
}
