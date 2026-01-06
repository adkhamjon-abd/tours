package org.example.controller;

import org.example.dto.BookingDTO;
import org.example.model.Booking;
import org.example.response.ApiResponse;
import org.example.service.abstractions.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingDTO>> createBooking(@RequestBody Booking booking) {
        BookingDTO createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdBooking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingDTO>> getBooking(@PathVariable("id") int id) {
        BookingDTO booking = bookingService.getBooking(id);
        return ResponseEntity.ok(new ApiResponse<>(booking));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<List<BookingDTO>>> getBookingsByUserId(@PathVariable("id") int id) {
        List<BookingDTO> bookings = bookingService.getBookingByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(bookings));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingDTO>>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(new ApiResponse<>(bookings));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingDTO>> updateBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        BookingDTO booking = bookingService.updateBooking(id, updateBooking);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(booking));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingDTO>> patchBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        BookingDTO booking = bookingService.patchBooking(id, updateBooking);
        return ResponseEntity.ok(new ApiResponse<>(booking));

    }
}
