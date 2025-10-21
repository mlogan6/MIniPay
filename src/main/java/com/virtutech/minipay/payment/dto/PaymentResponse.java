package com.virtutech.minipay.payment.dto;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long accountId,
        Double amount,
        String currency,
        String status,
        LocalDateTime createdAt
) {
}
