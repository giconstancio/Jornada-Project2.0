package com.copel.Jornada.Demanda;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {
    List<Demanda> findByFinalizadaFalse();
}
