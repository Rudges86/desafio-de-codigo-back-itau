package com.desafio.itau.service;

import com.desafio.itau.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;

class TransactionalServiceTest {
    private TransactionalService service;

    @BeforeEach
    void setup() {
        service = Mockito.mock(TransactionalService.class);

    }

    @Test
    @DisplayName("Testa a adição da transacao.")
    void addTransactional() {
        Transaction transaction = new Transaction(100.00, OffsetDateTime.now());
        service.addTransactional(transaction);
        verify(service,times(1)).addTransactional(any(Transaction.class));
    }

    @Test
    @DisplayName("Testa limpando a transacao.")
    void clearTransaction() {
        Transaction transaction = new Transaction(100.00, OffsetDateTime.now());
        DoubleSummaryStatistics mockSum = new DoubleSummaryStatistics();
        service.addTransactional(transaction);

        service.clearTransaction();
        verify(service, times(1)).clearTransaction();

        when(service.getStatistics()).thenReturn(mockSum);

        DoubleSummaryStatistics summaryStatistics = service.getStatistics();

        assertEquals(0.0,summaryStatistics.getSum());
        assertEquals(0,summaryStatistics.getCount());

    }

    @Test
    @DisplayName("Devolve as estatísticas da transação.")
    void getStatistics() {

        DoubleSummaryStatistics mockStatus = new DoubleSummaryStatistics();
        mockStatus.accept(100.00);
        when(service.getStatistics()).thenReturn(mockStatus);

        DoubleSummaryStatistics statistics = service.getStatistics();
        assertEquals(1,statistics.getCount());
        assertEquals(100.00,statistics.getSum());
    }
}