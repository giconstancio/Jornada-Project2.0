package com.copel.Jornada.Demanda;

import java.util.List;

import org.springframework.stereotype.Service;

import com.copel.Jornada.Fila.Fila;
import com.copel.Jornada.PesoCalculator.*;
import com.copel.Jornada.Problema.Problema;
import com.copel.Jornada.Util.FilaStatus;
import com.copel.Jornada.Demanda.DTO.DemandaRequestDTO;
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

        fila.getListaDeDemandas().add(demanda);
        fila.getListaDeDemandasSemFinalizadas().add(demanda);
        fila.filaViva();

        return demandaRepository.save(demanda);
    }

    public Demanda editarDemanda(Long id, Demanda novaDemanda) {
    for (Demanda d : fila.getListaDeDemandas()) {
        if (d.getId().equals(id)) {
            d.setNome(novaDemanda.getNome());
            d.setProblema(novaDemanda.getProblema());
            d.setDistanciaSede(novaDemanda.getDistanciaSede());
            d.setDistanciaVeiculo(novaDemanda.getDistanciaVeiculo());
            d.setCustoHoraParado(novaDemanda.getCustoHoraParado());
            d.setPecas(novaDemanda.getPecas());
            d.setMaoObra(novaDemanda.getMaoObra());
            d.setEquipamento(novaDemanda.getEquipamento());
            d.setRegiao(novaDemanda.getRegiao());
            d.setConsumoEnergia(novaDemanda.getConsumoEnergia());

            pesoCalculator.calcularPeso(d);
            fila.filaViva();
            return d;
            }
        }
        return null;
    }

    public boolean deletarDemanda(Long id) {
        Demanda paraRemover = null;
        for (Demanda d : fila.getListaDeDemandas()) {
            if (d.getId().equals(id)) {
                paraRemover = d;
                break;
            }
        }

        if (paraRemover != null) {
            fila.getListaDeDemandas().remove(paraRemover);
            fila.getListaDeDemandasSemFinalizadas().remove(paraRemover);
            fila.get_finished().remove(paraRemover);
            fila.filaViva();
            return true;
        }
        return false;
    }

    public List<Demanda> listarTodasAsDemandas() {
        return fila.getListaDeDemandas();
    }

    public Demanda retornarDemandaPeloNome(String nome) {
        for (Demanda d : fila.getListaDeDemandas()) {
            if (d.getNome().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        return null;
    }

    public String alterarStatusParaFinalizado(Demanda d) {
        fila.getListaDeDemandasSemFinalizadas().remove(d);
        d.setFila(FilaStatus.FINISHED);
        fila.get_finished().add(d);
        fila.filaViva();

        return "Demanda finalizada!";
    }

}
