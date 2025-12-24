package com.tcs.dto;

import com.tcs.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
    @NotNull public Long bookingId;
    @NotNull public Double amount;
    @NotNull public PaymentMethod method;
}
