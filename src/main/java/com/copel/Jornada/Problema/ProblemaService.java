package com.copel.Jornada.Problema;

import org.springframework.stereotype.Service;

import com.copel.Jornada.Problema.tipos.*;

@Service
public class ProblemaService {

    public Problema definirClasseProblema(int classeProblema) throws IllegalArgumentException {

        switch (classeProblema) {
            case 1:
                return new FaltaEnergia();
            case 2:
                return new ManutencaoPreventiva();
            case 3:
                return new FalhaTecnica();
            case 4:
                return new AtendimentoCliente();
            case 5:
                return new ExpansaoRede();
            default:
                throw new IllegalArgumentException("Tipo de problema inválido!");
        }
    }

}
