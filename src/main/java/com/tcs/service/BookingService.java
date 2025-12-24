package com.tcs.service;

import com.tcs.dto.BookingRequest;
import com.tcs.entity.*;
import com.tcs.exception.BadRequestException;
import com.tcs.exception.NotFoundException;
import com.tcs.repository.BookingRepository;
import com.tcs.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;

    public BookingService(BookingRepository bookingRepo, RoomRepository roomRepo) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> overlaps = bookingRepo.findOverlapping(room, checkIn, checkOut);
        return overlaps.isEmpty();
    }

    public Booking createBooking(UserAccount user, BookingRequest req) {
        Room room = roomRepo.findById(req.roomId).orElseThrow(() -> new NotFoundException("Room not found"));
        if (room.isOutOfService()) throw new BadRequestException("Room is out of service");
        LocalDate in = req.checkInDate;
        LocalDate out = req.checkOutDate;
        if (!out.isAfter(in)) throw new BadRequestException("checkOut must be after checkIn");

        if (!isRoomAvailable(room, in, out)) throw new BadRequestException("Room not available for selected dates");

        long nights = ChronoUnit.DAYS.between(in, out);
        if (nights <= 0) throw new BadRequestException("Stay must be at least 1 night");
        double total = nights * room.getPricePerNight();

        Booking b = new Booking();
        b.setUser(user);
        b.setRoom(room);
        b.setCheckInDate(in);
        b.setCheckOutDate(out);
        b.setStatus(BookingStatus.PENDING);
        b.setTotalAmount(total);
        b.setPaid(false);
        return bookingRepo.save(b);
    }

    public Booking confirm(Long bookingId) {
        Booking b = bookingRepo.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));
        if (b.getStatus() == BookingStatus.CANCELLED) throw new BadRequestException("Cannot confirm cancelled booking");
        b.setStatus(BookingStatus.CONFIRMED);
        return bookingRepo.save(b);
    }

    public Booking cancel(Long bookingId, UserAccount requester) {
        Booking b = bookingRepo.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));
        if (!requester.getRole().equals(Role.ADMIN) && !b.getUser().getId().equals(requester.getId())) {
            throw new BadRequestException("You can only cancel your own bookings");
        }
        b.setStatus(BookingStatus.CANCELLED);
        return bookingRepo.save(b);
    }

    public List<Booking> listByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> listAll() { return bookingRepo.findAll(); }
}
