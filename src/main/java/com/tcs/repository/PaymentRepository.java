package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}