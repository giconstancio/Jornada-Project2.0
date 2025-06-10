package com.copel.Jornada.Demanda;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {
    Optional<Demanda> findById(Long id);
    List<Demanda> findByFilaOrderByPesoDesc(int fila);
    int countByFila(int fila);
    List<Demanda> findByFilaIn(List<Integer> filas);
}
