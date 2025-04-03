package com.desafio.itau.service;

import com.desafio.itau.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionalService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();

    public void addTransactional(Transaction transaction) {
        transactions.add(transaction);
    }


}
