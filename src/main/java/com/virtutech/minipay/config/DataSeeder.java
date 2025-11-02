package com.virtutech.minipay.config;

import com.virtutech.minipay.payment.entity.Payment;
import com.virtutech.minipay.payment.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedData(PaymentRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Payment(null, 101L, 150.00, "USD", "SUCCESS", LocalDateTime.now()));
                repo.save(new Payment(null, 202L, 320.50, "EUR", "PENDING", LocalDateTime.now()));
                repo.save(new Payment(null, 303L, 99.99, "GBP", "FAILED", LocalDateTime.now()));
                System.out.println("🌱 Seeded 3 payments into the database.");
            }
        };
    }
}
