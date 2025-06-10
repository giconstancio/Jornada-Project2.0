package com.copel.Jornada.Demanda;

import java.util.List;

import org.springframework.stereotype.Service;

import com.copel.Jornada.Fila.Fila;
import com.copel.Jornada.PesoCalculator.*;
import com.copel.Jornada.Problema.Problema;
import com.copel.Jornada.Demanda.DTO.DemandaRequestDTO;
import com.copel.Jornada.Demanda.DTO.DemandaUpdateDTO;
import com.copel.Jornada.Demanda.Regiao.*;
import com.copel.Jornada.Demanda.componentes.ConsumoEnergia;
import com.copel.Jornada.Demanda.componentes.Equipamento;
import com.copel.Jornada.Demanda.componentes.MaoObra;
import com.copel.Jornada.Demanda.componentes.Pecas;
import com.copel.Jornada.Problema.ProblemaRepository;

@Service
public class DemandaService {

    private final Fila fila;
    private final PesoCalculator pesoCalculator;
    private final RegiaoRepository regiaoRepository;
    private final ProblemaRepository problemaRepository;
    private final DemandaRepository demandaRepository;

    public DemandaService(Fila fila, PesoCalculator pesoCalculator, RegiaoRepository regiaoRepository, ProblemaRepository problemaRepository, DemandaRepository demandaRepository) {
        this.fila = fila;
        this.pesoCalculator = pesoCalculator;
        this.regiaoRepository = regiaoRepository;
        this.problemaRepository = problemaRepository;
        this.demandaRepository = demandaRepository;
    }

    public Demanda adicionarDemanda(DemandaRequestDTO dto) {
        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new IllegalArgumentException("Região com ID " + dto.getRegiaoId() + " não encontrada"));

        Problema problema = problemaRepository.findById(dto.getProblemaId())
                .orElseThrow(() -> new IllegalArgumentException("Problema com ID " + dto.getProblemaId() + " não encontrado"));

        Demanda demanda = new Demanda();
        demanda.setNome(dto.getNome());
        demanda.setProblema(problema);
        demanda.setDistanciaSede(dto.getDistanciaSede());
        demanda.setDistanciaVeiculo(dto.getDistanciaVeiculo());
        demanda.setCustoHoraParado(dto.getCustoHoraParado());
        demanda.setPecas(new Pecas(dto.getCustoPecas()));
        demanda.setMaoObra(new MaoObra(dto.getCustoMaoObra()));
        demanda.setEquipamento(new Equipamento(dto.getCustoEquipamento()));
        demanda.setConsumoEnergia(new ConsumoEnergia(dto.getConsumoEnergia()));
        demanda.setRegiao(regiao);
        demanda.setFila(dto.getFila());

        pesoCalculator.calcularPeso(demanda);

        fila.adicionarNovaDemanda(demanda);

        return demandaRepository.save(demanda);
    } 

    public Demanda editarDemanda(Long id, DemandaUpdateDTO dtoUpdate) {
        for (Demanda d : fila.getTodasDemandas()) {
            if (d.getId().equals(id)) {

                if (dtoUpdate.getProblemaId() != null) {
                    Problema problema = problemaRepository.findById(dtoUpdate.getProblemaId())
                        .orElseThrow(() -> new IllegalArgumentException("Problema com ID " + dtoUpdate.getProblemaId() + " não encontrado"));
                    d.setProblema(problema);
                }

                if (dtoUpdate.getNome() != null) {
                    d.setNome(dtoUpdate.getNome());
                }

                if (dtoUpdate.getPeso() != null) {
                    d.setPeso(dtoUpdate.getPeso());
                }

                return demandaRepository.save(d);
            }
        }
        return null;
    }

    public void deletarDemanda(Long id) {
        demandaRepository.deleteById(id);
    }

    public List<Demanda> listarTodas() {
        return demandaRepository.findAll();
    }

    public Demanda buscarPorId(Long id) {
        return demandaRepository.findById(id).orElseThrow(() -> new RuntimeException("Demanda não encontrada."));
    }

}
