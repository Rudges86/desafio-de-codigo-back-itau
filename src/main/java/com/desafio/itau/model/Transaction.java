package com.desafio.itau.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    private double valor;
    private OffsetDateTime dataHora;
}
