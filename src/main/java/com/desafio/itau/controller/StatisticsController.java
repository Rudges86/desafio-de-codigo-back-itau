package com.desafio.itau.controller;

import com.desafio.itau.dto.StatisticsResponse;
import com.desafio.itau.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    @Autowired
    private TransactionalService service;

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics() {
        return ResponseEntity.ok().body(new StatisticsResponse(service.getStatistics()));
    }
}
