package org.example.controller;

import org.example.model.Booking;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.response.ApiResponse;
import org.example.service.abstractions.BookingService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
    public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking){
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdBooking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBooking(@PathVariable("id") int id) {
        Booking booking = bookingService.getBooking(id);
        return ResponseEntity.ok(new ApiResponse<>(booking));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByUserId(@PathVariable("id") int id) {
        List<Booking> bookings = bookingService.getBookingByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(bookings));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(new ApiResponse<>(bookings));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> updateBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.updateBooking(id, updateBooking);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(booking));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> patchBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.patchBooking(id, updateBooking);
        return ResponseEntity.ok(new ApiResponse<>(booking));

    }
}
