package com.tcs.dto;

import com.tcs.entity.RoomType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomRequest {
    @NotNull public Long hotelId;
    @NotBlank public String number;
    @NotNull public RoomType type;
    @NotNull public Integer capacity;
    @NotNull public Double pricePerNight;
    public Boolean outOfService = false;
}
