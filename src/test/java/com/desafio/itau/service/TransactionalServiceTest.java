package com.desafio.itau.service;

import com.desafio.itau.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;

class TransactionalServiceTest {

    private TransactionalService service;

    @BeforeEach
    void setup() {
        //Mocando o Serviço
        // service = Mockito.mock(TransactionalService.class);
        service = new TransactionalService();
    }

    @Test
    @DisplayName("Testa a adição da transacao.")
    void addTransactional() {
        Transaction transaction = new Transaction(100.00, OffsetDateTime.now());
        service.addTransactional(transaction);
        //Teste com Mock
        // verify(service,times(1)).addTransactional(any(Transaction.class));

        DoubleSummaryStatistics stats = service.getStatistics(60L);

        assertEquals(1, stats.getCount());
        assertEquals(100.00, stats.getSum());
    }

    @Test
    @DisplayName("Testa limpando a transacao.")
    void clearTransaction() {
        /*Teste com Mock
        DoubleSummaryStatistics mockSum = new DoubleSummaryStatistics();
        verify(service, times(1)).clearTransaction();
        when(service.getStatistics(60L)).thenReturn(mockSum);
         */
        Transaction transaction = new Transaction(100.00, OffsetDateTime.now());

        service.addTransactional(transaction);
        service.clearTransaction();

        DoubleSummaryStatistics summaryStatistics = service.getStatistics(60L);
        assertEquals(0.0,summaryStatistics.getSum());
        assertEquals(0,summaryStatistics.getCount());

    }

    @Test
    @DisplayName("Devolve as estatísticas da transação.")
    void getStatistics() {

        /* teste mocado
        DoubleSummaryStatistics mockStatus = new DoubleSummaryStatistics();
        mockStatus.accept(100.00);
        when(service.getStatistics(60L)).thenReturn(mockStatus);
        */

        //Teste sem mock
        Transaction transaction = new Transaction(100.00, OffsetDateTime.now());
        service.addTransactional(transaction);

        DoubleSummaryStatistics statistics = service.getStatistics(60L);
        assertEquals(1,statistics.getCount());
        assertEquals(100.00,statistics.getSum());
    }
}