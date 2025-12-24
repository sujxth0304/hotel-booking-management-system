package com.tcs.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Booking booking;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.SUCCESS;

    private LocalDateTime paidAt = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Booking getBooking() { return booking; } public void setBooking(Booking booking) { this.booking = booking; }
    public Double getAmount() { return amount; } public void setAmount(Double amount) { this.amount = amount; }
    public PaymentMethod getMethod() { return method; } public void setMethod(PaymentMethod method) { this.method = method; }
    public PaymentStatus getStatus() { return status; } public void setStatus(PaymentStatus status) { this.status = status; }
    public LocalDateTime getPaidAt() { return paidAt; } public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
