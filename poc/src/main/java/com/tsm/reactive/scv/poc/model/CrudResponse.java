package com.tsm.reactive.scv.poc.model;

import com.tsm.reactive.scv.poc.entity.Utente;

import java.util.List;

public record CrudResponse(List<Utente> utenti) {
}
