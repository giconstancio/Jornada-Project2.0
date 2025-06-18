package com.copel.Jornada.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.copel.Jornada.Fila.FilaService;
import com.copel.Jornada.PesoCalculator.PesoCalculator;

@Component
public class RedistribuicaoDemandas {
    
    private final FilaService filaService;
    private final PesoCalculator pesoCalculator;
    private static final Logger logger = LoggerFactory.getLogger(RedistribuicaoDemandas.class);

    public RedistribuicaoDemandas(FilaService filaService, PesoCalculator pesoCalculator) {
        this.filaService = filaService;
        this.pesoCalculator = pesoCalculator;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void executarRedistribuicao() {
        pesoCalculator.recalcularPesos();
        logger.info("Pesos recalculados com sucesso.");

        filaService.redistribuirFilas();
        logger.info("Filas redistribuídas com sucesso.");
    }
}
