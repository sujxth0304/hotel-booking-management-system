package com.tcs.controller;

import com.tcs.dto.RoomRequest;
import com.tcs.entity.Room;
import com.tcs.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service) { this.service = service; }

    @GetMapping("/by-hotel/{hotelId}")
    public List<Room> listByHotel(@PathVariable Long hotelId) {
        return service.listByHotel(hotelId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Room create(@Valid @RequestBody RoomRequest req) {
        return service.create(req);
    }

    @GetMapping("/{roomId}/availability")
    public boolean availability(@PathVariable Long roomId,
                                @RequestParam LocalDate checkIn,
                                @RequestParam LocalDate checkOut) {
        return service.isAvailable(roomId, checkIn, checkOut);
    }
}
