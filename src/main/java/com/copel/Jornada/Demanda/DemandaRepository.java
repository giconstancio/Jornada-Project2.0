package com.copel.Jornada.Demanda;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {
    Optional<Demanda> findById(Long id);
}
