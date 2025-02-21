package com.tsm.reactive.scv.poc.controller;

import com.tsm.reactive.scv.poc.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crud")
@RequiredArgsConstructor
public class CrudController {

    private final CrudService crudService;

}
