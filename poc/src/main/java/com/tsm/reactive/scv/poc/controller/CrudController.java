package com.tsm.reactive.scv.poc.controller;

import com.tsm.reactive.scv.poc.model.BaseResponse;
import com.tsm.reactive.scv.poc.model.CrudResponse;
import com.tsm.reactive.scv.poc.model.UtenteRequest;
import com.tsm.reactive.scv.poc.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/crud")
@RequiredArgsConstructor
public class CrudController {

    private final CrudService crudService;

    @PostMapping("save")
    public ResponseEntity<BaseResponse> addUtente(@RequestBody UtenteRequest request){
        return ResponseEntity.ok(crudService.addUtente(request));
    }

    @GetMapping("get")
    public ResponseEntity<CrudResponse> getUtente(@RequestParam (required = false) Long id, @RequestParam(required = false) String nome){
        return ResponseEntity.ok(crudService.getUtente(id,nome));
    }
}
