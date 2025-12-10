package org.example.controller;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.service.BookingService;
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

    public BookingController(BookingRepository bookingRepository,
                             UserRepository userRepository,
                             TourRepository tourRepository,
                             BookingService bookingService){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public String createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable("id") int id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/users/{id}")
    public List<Booking> getBookingsByUserId(@PathVariable("id") int id) {
        return bookingService.getBookingByUserId(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        return bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.updateBooking(id, updateBooking);
        if (booking == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(booking);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Booking> patchBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.patchUser(id, updateBooking);

        if (booking == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(booking);
    }
}
