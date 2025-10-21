package com.virtutech.minipay.payment.service.impl;

import com.virtutech.minipay.payment.dto.PaymentRequest;
import com.virtutech.minipay.payment.dto.PaymentResponse;
import com.virtutech.minipay.payment.entity.Payment;
import com.virtutech.minipay.payment.repository.PaymentRepository;
import com.virtutech.minipay.payment.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAccountId(request.accountId());
    }
}
