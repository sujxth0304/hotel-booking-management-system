package com.tcs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Hotel hotel;

    @NotBlank
    private String number; // e.g., "101"

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoomType type;

    @NotNull
    private Integer capacity;

    @NotNull
    private Double pricePerNight;

    private boolean outOfService = false;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Hotel getHotel() { return hotel; } public void setHotel(Hotel hotel) { this.hotel = hotel; }
    public String getNumber() { return number; } public void setNumber(String number) { this.number = number; }
    public RoomType getType() { return type; } public void setType(RoomType type) { this.type = type; }
    public Integer getCapacity() { return capacity; } public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Double getPricePerNight() { return pricePerNight; } public void setPricePerNight(Double pricePerNight) { this.pricePerNight = pricePerNight; }
    public boolean isOutOfService() { return outOfService; } public void setOutOfService(boolean outOfService) { this.outOfService = outOfService; }
}
