package com.virtutech.minipay.payment.service;

import com.virtutech.minipay.payment.dto.PaymentRequest;
import com.virtutech.minipay.payment.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);

}
