package com.tcs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserAccount user;

    @ManyToOne(optional = false)
    private Room room;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    private Double totalAmount;

    private boolean paid = false;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public UserAccount getUser() { return user; } public void setUser(UserAccount user) { this.user = user; }
    public Room getRoom() { return room; } public void setRoom(Room room) { this.room = room; }
    public LocalDate getCheckInDate() { return checkInDate; } public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; } public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
    public BookingStatus getStatus() { return status; } public void setStatus(BookingStatus status) { this.status = status; }
    public Double getTotalAmount() { return totalAmount; } public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public boolean isPaid() { return paid; } public void setPaid(boolean paid) { this.paid = paid; }
}
