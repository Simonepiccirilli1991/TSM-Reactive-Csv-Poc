package com.tsm.reactive.scv.poc.repo;

import com.tsm.reactive.scv.poc.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepo extends JpaRepository<Utente,Long> {

    @Query(value = "SELECT c FROM utente c WHERE c.nome = :nome",nativeQuery = true)
    List<Utente> findUtenteByNome(@Param("nome") String nome);
}
