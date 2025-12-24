package com.tcs.dto;

import jakarta.validation.constraints.NotBlank;

public class HotelRequest {
    @NotBlank public String name;
    @NotBlank public String address;
    @NotBlank public String city;
    public String description;
}
