package com.desafio.itau.controller;

import com.desafio.itau.model.Transaction;
import com.desafio.itau.service.TransactionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
class StatisticsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionalService serviceMock;

    @InjectMocks
    private StatisticsController controller;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach()
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Testando as estatísticas")
    void getStatistics() throws  Exception {
        DoubleSummaryStatistics status = new DoubleSummaryStatistics();
        status.accept(500.0);
        when(serviceMock.getStatistics(60L)).thenReturn(status);

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.sum").value(500.0));
    }
}