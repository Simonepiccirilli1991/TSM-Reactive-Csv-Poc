package com.tsm.reactive.scv.poc.service;

import com.tsm.reactive.scv.poc.entity.Utente;
import com.tsm.reactive.scv.poc.exception.CsvException;
import com.tsm.reactive.scv.poc.model.BaseResponse;
import com.tsm.reactive.scv.poc.repo.UtenteRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@Slf4j
@RequiredArgsConstructor
public class CsvService {

    private final UtenteRepo utenteRepo;

    // classico
    @Transactional
    public BaseResponse uploadFromCsv(MultipartFile csvFile){
        log.info("UploadFromCsv file started");
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);
            for (CSVRecord recordCsv : records) {
                var utente = new Utente();
                utente.setId(Long.valueOf(recordCsv.get("id")));
                utente.setNome(recordCsv.get("nome"));
                utente.setCognome(recordCsv.get("cognome"));
                utente.setDataNascita(recordCsv.get("dataNascita"));
                utente.setDataInserimento(recordCsv.get("dataInserimento"));
                // Parse additional fields as needed
                utenteRepo.save(utente);
            }
        } catch (Exception e) {
            throw new CsvException("Failed to store CSV data: " + e.getMessage());
        }

        log.info("UploadFromCsv file ended successfully");
        return new BaseResponse("Caricato correttamente");
    }

    // reattivo
    @Transactional
    public BaseResponse uploadFromCsvReactive(MultipartFile csvFile){
        log.info("UploadFromCsv file started");
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            Flux.fromIterable(records)
                    // Run calls in parallel on a bounded elastic scheduler
                    .parallel()
                    // qui lascio fare la gestione a spring che sa che il thread dovra essere distrutto
                    .runOn(Schedulers.boundedElastic())
                    .flatMap(recordCsv -> {
                        var utente = new Utente();
                        utente.setId(Long.valueOf(recordCsv.get("id")));
                        utente.setNome(recordCsv.get("nome"));
                        utente.setCognome(recordCsv.get("cognome"));
                        utente.setDataNascita(recordCsv.get("dataNascita"));
                        utente.setDataInserimento(recordCsv.get("dataInserimento"));
                        // Parse additional fields as needed
                        utenteRepo.save(utente);
                        return null;
                            }
                    )
                    // Merge parallel results back into a sequential Flux
                    .sequential()
                    // Collect all items into a List and block until complete
                    .collectList()
                    .block();
        } catch (Exception e) {
            throw new CsvException("Failed to store CSV data: " + e.getMessage());
        }

        log.info("UploadFromCsv file ended successfully");
        return new BaseResponse("Caricato correttamente");
    }
}
