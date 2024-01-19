package com.flight.reservation.controller;

import com.flight.reservation.model.Booking;
import com.flight.reservation.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(
            @RequestParam Long passengerId,
            @RequestParam Long flightId) {
        try {
            Booking booking = bookingService.createBooking(passengerId, flightId);
            return ResponseEntity.ok("Booking created successfully with ID: " + booking.getId());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating booking: " + e.getMessage());
        }
    }

    @PostMapping("/createmultiple")
    public ResponseEntity<String> createBookings(
            @RequestParam List<Long> passengerIds,
            @RequestParam Long flightId) {
        try {
            List<Booking> bookings = bookingService.createBookingsForMultiplePassengers(passengerIds, flightId);
            return ResponseEntity.ok("Bookings created successfully with IDs: " +
                    bookings.stream().map(Booking::getId).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating bookings: " + e.getMessage());
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);
            return ResponseEntity.ok(booking);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking canceled successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error canceling booking: " + e.getMessage());
        }
    }

    @GetMapping("/getbookingbyPassengerId")

    public List<Booking> getBookingbyPassengerId(@RequestParam Long passengerId) {
        return bookingService.getBookingsById(passengerId);
    }
}
