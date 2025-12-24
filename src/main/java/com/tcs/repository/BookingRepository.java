package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Ensure this is imported
import org.springframework.data.repository.query.Param;

import com.tcs.entity.Booking;
import com.tcs.entity.BookingStatus;
import com.tcs.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("""
	           SELECT b FROM Booking b 
	           WHERE b.room = :room 
	           AND b.status <> com.tcs.entity.BookingStatus.CANCELLED 
	           AND b.checkInDate < :checkOut 
	           AND b.checkOutDate > :checkIn
	           """)
	    List<Booking> findOverlapping(
	        @Param("room") Room room, 
	        @Param("checkIn") LocalDate checkIn, 
	        @Param("checkOut") LocalDate checkOut
	    );

    List<Booking> findByUserId(Long userId);

    List<Booking> findByStatus(BookingStatus status);
}