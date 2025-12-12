package org.example.controller;

import org.example.model.Booking;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.response.ApiResponse;
import org.example.service.BookingService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
    public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingRepository bookingRepository,
                             UserRepository userRepository,
                             TourRepository tourRepository,
                             BookingService bookingService,
                             UserService userService){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBooking(@PathVariable("id") int id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByUserId(@PathVariable("id") int id) {
        return bookingService.getBookingByUserId(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        return bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> updateBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        return bookingService.updateBooking(id, updateBooking);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> patchBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        return bookingService.patchBooking(id, updateBooking);
    }
}
