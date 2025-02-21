package com.tsm.reactive.scv.poc.repo;

import com.tsm.reactive.scv.poc.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<Utente,Long> {
}
