package org.iftm.clientapp.repositories;

import org.iftm.clientapp.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepository extends JpaRepository<Nota, Long>{

    
}
