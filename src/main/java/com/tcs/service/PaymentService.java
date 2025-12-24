package com.tcs.service;

import com.tcs.dto.PaymentRequest;
import com.tcs.entity.Booking;
import com.tcs.entity.Payment;
import com.tcs.entity.PaymentStatus;
import com.tcs.exception.BadRequestException;
import com.tcs.exception.NotFoundException;
import com.tcs.repository.BookingRepository;
import com.tcs.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final BookingRepository bookingRepo;

    public PaymentService(PaymentRepository paymentRepo, BookingRepository bookingRepo) {
        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
    }

    public Payment pay(PaymentRequest req) {
        Booking b = bookingRepo.findById(req.bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));
        if (b.getStatus() == null || b.getStatus().name().equals("CANCELLED")) {
            throw new BadRequestException("Cannot pay for cancelled booking");
        }
        if (req.amount < b.getTotalAmount()) {
            throw new BadRequestException("Insufficient amount");
        }
        Payment p = new Payment();
        p.setBooking(b);
        p.setAmount(req.amount);
        p.setMethod(req.method);
        p.setStatus(PaymentStatus.SUCCESS);
        b.setPaid(true);
        bookingRepo.save(b);
        return paymentRepo.save(p);
    }
}
