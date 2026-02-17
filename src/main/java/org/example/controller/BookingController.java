package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.request.CreateBookingRequest;
import org.example.dto.request.UpdateBookingRequest;
import org.example.model.Booking;
import org.example.response.ApiResponse;
import org.example.dto.response.BookingResponse;
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
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@RequestBody @Valid CreateBookingRequest booking) {
        BookingResponse createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdBooking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBooking(@PathVariable("id") int id) {
        BookingResponse booking = bookingService.getBooking(id);
        return ResponseEntity.ok(new ApiResponse<>(booking));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByUserId(@PathVariable("id") int id) {
        List<BookingResponse> bookings = bookingService.getBookingByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(bookings));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(new ApiResponse<>(bookings));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBooking(@PathVariable("id") int id, @RequestBody UpdateBookingRequest updateBooking) {
        BookingResponse booking = bookingService.updateBooking(id, updateBooking);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(booking));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> patchBooking(@PathVariable("id") int id, @RequestBody UpdateBookingRequest updateBooking) {
        BookingResponse booking = bookingService.patchBooking(id, updateBooking);
        return ResponseEntity.ok(new ApiResponse<>(booking));

    }
}
