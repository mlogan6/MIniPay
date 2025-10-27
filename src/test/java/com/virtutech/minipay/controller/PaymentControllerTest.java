package com.virtutech.minipay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtutech.minipay.payment.controller.PaymentController;
import com.virtutech.minipay.payment.dto.PaymentRequest;
import com.virtutech.minipay.payment.dto.PaymentResponse;
import com.virtutech.minipay.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    void shouldCreatePayment() throws Exception {
        PaymentRequest request = new PaymentRequest(1L, 100.0, "USD");
        PaymentResponse response = new PaymentResponse(
                1L, 1L, 100.0, "USD", "PENDING", LocalDateTime.now()
        );

        Mockito.when(paymentService.createPayment(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.currency").value("USD"));
    }

    @Test
    void shouldReturnAllPayments() throws Exception {
        List<PaymentResponse> responses = List.of(
                new PaymentResponse(1L, 1L, 100.0, "USD", "SUCCESS", LocalDateTime.now())
        );

        Mockito.when(paymentService.getAllPayments()).thenReturn(responses);

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("SUCCESS"));
    }

    @Test
    void shouldReturnPaymentById() throws Exception {
        PaymentResponse response = new PaymentResponse(1L, 1L, 200.0, "EUR", "PENDING", LocalDateTime.now());

        Mockito.when(paymentService.getPaymentById(1L)).thenReturn(response);

        mockMvc.perform(get("/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(200.0))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

}
