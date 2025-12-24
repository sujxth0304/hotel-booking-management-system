package com.tcs.controller;

import com.tcs.dto.BookingRequest;
import com.tcs.entity.Booking;
import com.tcs.entity.UserAccount;
import com.tcs.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService service;
    public BookingController(BookingService service) { this.service = service; }

    @PostMapping
    public Booking create(@AuthenticationPrincipal UserAccount user,
                          @Valid @RequestBody BookingRequest req) {
        return service.createBooking(user, req);
    }

    @PostMapping("/{id}/confirm")
    @PreAuthorize("hasRole('ADMIN')")
    public Booking confirm(@PathVariable Long id) {
        return service.confirm(id);
    }

    @PostMapping("/{id}/cancel")
    public Booking cancel(@PathVariable Long id, @AuthenticationPrincipal UserAccount user) {
        return service.cancel(id, user);
    }

    @GetMapping("/me")
    public List<Booking> myBookings(@AuthenticationPrincipal UserAccount user) {
        return service.listByUser(user.getId());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> listAll() { return service.listAll(); }
}
