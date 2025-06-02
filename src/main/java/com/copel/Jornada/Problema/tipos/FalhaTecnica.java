package com.copel.Jornada.Problema.tipos;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "falha_tecnica")
public class FalhaTecnica extends Problema {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void definirAtributos(Demanda d) {
        d.getProblema().setDescricao("Equipamento da rede com falha técnica (transformador, poste, etc)");
        d.getProblema().setTempoEspera(2);
        d.getProblema().setTempoMedioAtendimento(4);
    }

    public Long getId() {
        return id;
    }
}
