package org.iftm.clientapp.repositories;

import org.iftm.clientapp.entities.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long>{

}
