package com.tcs.controller;


import com.tcs.dto.PaymentRequest;
import com.tcs.entity.Payment;
import com.tcs.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;
    public PaymentController(PaymentService service) { this.service = service; }

    @PostMapping
    public Payment pay(@Valid @RequestBody PaymentRequest req) {
        return service.pay(req);
    }
}
