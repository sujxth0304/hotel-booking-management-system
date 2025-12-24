package com.tcs.controller;

import com.tcs.dto.HotelRequest;
import com.tcs.entity.Hotel;
import com.tcs.entity.UserAccount;
import com.tcs.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService service;
    public HotelController(HotelService service) { this.service = service; }

    @GetMapping
    public List<Hotel> list(@RequestParam(required = false) String city) {
        return service.list(city);
    }

    @GetMapping("/{id}")
    public Hotel get(@PathVariable Long id) { return service.get(id); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Hotel create(@Valid @RequestBody HotelRequest req) {
        return service.create(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Hotel update(@PathVariable Long id, @Valid @RequestBody HotelRequest req) {
        return service.update(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
