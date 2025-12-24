package com.tcs.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {
    @NotNull public Long roomId;
    @NotNull public LocalDate checkInDate;
    @NotNull public LocalDate checkOutDate;
}
