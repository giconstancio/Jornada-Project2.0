package com.copel.Jornada.Problema.tipos;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "falta_energia")
public class FaltaEnergia extends Problema {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void definirAtributos(Demanda d) {
        d.getProblema().setDescricao("Falta de energia em uma ou mais residências/regiões");
        d.getProblema().setTempoEspera(1);
        d.getProblema().setTempoMedioAtendimento(2);
    }

    public Long getId() {
        return id;
    }
}
