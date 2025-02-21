package com.tsm.reactive.scv.poc.service;

import com.tsm.reactive.scv.poc.entity.Utente;
import com.tsm.reactive.scv.poc.exception.CsvException;
import com.tsm.reactive.scv.poc.model.BaseResponse;
import com.tsm.reactive.scv.poc.model.CrudResponse;
import com.tsm.reactive.scv.poc.model.UtenteRequest;
import com.tsm.reactive.scv.poc.repo.UtenteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrudService {

    private final UtenteRepo utenteRepo;

    public BaseResponse addUtente(UtenteRequest request){
        log.info("AddUtente service started with raw request: {}",request);

        var utente = new Utente();
        utente.setNome(request.nome());
        utente.setCognome(request.cognome());
        utente.setDataNascita(request.dataNascita());
        utente.setDataInserimento(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));

        utenteRepo.save(utente);

        log.info("AddUtente service ended successfully");
        return new BaseResponse("Utente added successufully");
    }

    public CrudResponse getUtente(Long id, String nome){
        log.info("GetUtente service started with raw request, id: {}, or name: {}",id,nome);
        // checko per id
        if(!ObjectUtils.isEmpty(id)){
            var utente = utenteRepo.findById(id)
                    .orElseThrow(() -> {
                        log.error("Error on getUtente service, utente not found with id: {}",id);
                        throw new CsvException("Error on get Utente, utente not found with id");
                    });

            var resp = new CrudResponse(List.of(utente));
            log.info("GetUtenteService ended successfully");
            return resp;
        }
        // se id non c'e va per name
        var utenti = utenteRepo.findUtenteByNome(nome);
        if(utenti.isEmpty()){
            log.error("Error on getUtente service, utente not found with nome: {}",nome);
            throw new CsvException("Error on get Utente, utente not found with nome");
        }
        var resp = new CrudResponse(utenti);
        log.info("GetUtenteService ended successfully");
        return resp;
    }
}
