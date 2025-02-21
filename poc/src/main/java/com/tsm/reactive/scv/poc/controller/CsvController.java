package com.tsm.reactive.scv.poc.controller;

import com.tsm.reactive.scv.poc.model.BaseResponse;
import com.tsm.reactive.scv.poc.service.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/csv")
@RequiredArgsConstructor
public class CsvController {

    private final CsvService csvService;

    @PostMapping("upload")
    public ResponseEntity<BaseResponse> upload(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(csvService.uploadFromCsv(file));
    }

    @PostMapping("upload/reactive")
    public ResponseEntity<BaseResponse> uplaodReactive(@RequestParam ("file") MultipartFile file){
        return ResponseEntity.ok(csvService.uploadFromCsvReactive(file));
    }
}
