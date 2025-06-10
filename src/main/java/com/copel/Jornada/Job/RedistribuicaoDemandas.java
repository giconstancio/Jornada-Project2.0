package com.copel.Jornada.Job;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.copel.Jornada.Fila.FilaService;
import com.copel.Jornada.PesoCalculator.PesoCalculator;

@Component
public class RedistribuicaoDemandas {
    
    private final FilaService filaService;
    private final PesoCalculator pesoCalculator;

    public RedistribuicaoDemandas(FilaService filaService, PesoCalculator pesoCalculator) {
        this.filaService = filaService;
        this.pesoCalculator = pesoCalculator;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void executarRedistribuicao() {
        System.out.println("🔄 [Job] Iniciando recálculo de pesos às " + LocalDateTime.now());
        pesoCalculator.recalcularPesos(); 
        filaService.redistribuirFilas(); 
        System.out.println("Redistribuição concluída.");
    }
}
