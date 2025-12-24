package com.tcs.service;

import com.tcs.dto.RoomRequest;
import com.tcs.entity.Hotel;
import com.tcs.entity.Room;
import com.tcs.exception.BadRequestException;
import com.tcs.exception.NotFoundException;
import com.tcs.repository.HotelRepository;
import com.tcs.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepo;
    private final HotelRepository hotelRepo;
    private final BookingService bookingService;

    public RoomService(RoomRepository roomRepo, HotelRepository hotelRepo, BookingService bookingService) {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
        this.bookingService = bookingService;
    }

    public Room create(RoomRequest req) {
        Hotel hotel = hotelRepo.findById(req.hotelId)
                .orElseThrow(() -> new NotFoundException("Hotel not found"));
        Room r = new Room();
        r.setHotel(hotel);
        r.setNumber(req.number);
        r.setType(req.type);
        r.setCapacity(req.capacity);
        r.setPricePerNight(req.pricePerNight);
        r.setOutOfService(Boolean.TRUE.equals(req.outOfService));
        return roomRepo.save(r);
    }

    public List<Room> listByHotel(Long hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new NotFoundException("Hotel not found"));
        return roomRepo.findByHotel(hotel);
    }

    public Room get(Long id) {
        return roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
    }

    public boolean isAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        if (!checkOut.isAfter(checkIn)) throw new BadRequestException("checkOut must be after checkIn");
        Room room = get(roomId);
        if (room.isOutOfService()) return false;
        return bookingService.isRoomAvailable(room, checkIn, checkOut);
    }
}
