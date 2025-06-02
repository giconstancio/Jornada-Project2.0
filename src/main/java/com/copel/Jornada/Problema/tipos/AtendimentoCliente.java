package com.copel.Jornada.Problema.tipos;

import com.copel.Jornada.Demanda.Demanda;
import com.copel.Jornada.Problema.Problema;

import jakarta.persistence.*;

@Entity
@Table(name = "atendimento_cliente")
public class AtendimentoCliente extends Problema {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void definirAtributos(Demanda d) {
        d.getProblema().setDescricao("Solicitação de vistoria, mudança de titularidade, ligação nova etc");
        d.getProblema().setTempoEspera(2);
        d.getProblema().setTempoMedioAtendimento(3);
    }

    public Long getId() {
        return id;
    }
}
