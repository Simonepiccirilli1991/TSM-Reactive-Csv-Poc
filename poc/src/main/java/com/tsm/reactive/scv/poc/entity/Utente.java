package com.tsm.reactive.scv.poc.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "utente")
@Data
public class Utente {

    @Id
    private Long id;
    private String nome;
    private String cognome;
    @Column(name = "dataNascita")
    private String dataNascita;
    private String dataInserimento;


}
