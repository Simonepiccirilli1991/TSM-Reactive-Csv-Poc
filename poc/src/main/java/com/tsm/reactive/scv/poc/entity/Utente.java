package com.tsm.reactive.scv.poc.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "utente")
@Data
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    @Column(name = "dataNascita")
    private String dataNascita;
    private String dataInserimento;
}
