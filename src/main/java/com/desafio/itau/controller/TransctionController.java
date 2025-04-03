package com.desafio.itau.controller;

import com.desafio.itau.dto.TransactionRequest;
import com.desafio.itau.model.Transaction;
import com.desafio.itau.service.TransactionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransctionController {

    @Autowired
    private TransactionalService service;

    @PostMapping()
    public ResponseEntity<Void> createdTransactional(@Valid @RequestBody TransactionRequest transactionRequest) {
        if(transactionRequest.getDataHora().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        service.addTransactional(new Transaction(transactionRequest.getValor(),transactionRequest.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
