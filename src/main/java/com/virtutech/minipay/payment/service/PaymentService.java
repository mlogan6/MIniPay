package com.virtutech.minipay.payment.service;

import com.virtutech.minipay.payment.dto.PaymentRequest;
import com.virtutech.minipay.payment.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    PaymentResponse updatePayment(Long id, PaymentRequest request);

    void deletePayment(Long id);


}
