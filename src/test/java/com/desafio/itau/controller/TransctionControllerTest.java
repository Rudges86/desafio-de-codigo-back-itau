package com.desafio.itau.controller;

import com.desafio.itau.dto.TransactionRequest;
import com.desafio.itau.model.Transaction;
import com.desafio.itau.service.TransactionalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TransctionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TransactionalService serviceMock;

    @InjectMocks
    private TransctionController controller;

    private final ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Teste de adição de transação.")
    void testAddTranscao() throws Exception {
        TransactionRequest request = new TransactionRequest(100, OffsetDateTime.now());
        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(serviceMock, times(1)).addTransactional(any(Transaction.class));
    }
    @Test
    @DisplayName("Teste de deleção.")
    void testDelecao() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());

        verify(serviceMock, times(1)).clearTransaction();
    }

    @Test
    void testIncluirTransacaoFutura() throws Exception {
        TransactionRequest request = new TransactionRequest(100, OffsetDateTime.now().plusHours(2));
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity());
        verify(serviceMock, times(0)).addTransactional(any(Transaction.class));
    }

}