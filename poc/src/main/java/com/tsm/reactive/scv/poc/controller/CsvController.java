package com.tsm.reactive.scv.poc.controller;

import com.tsm.reactive.scv.poc.service.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/csv")
@RequiredArgsConstructor
public class CsvController {

    private final CsvService csvService;
}
