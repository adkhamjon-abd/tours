package org.example.controller;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.service.BookingService;
import org.springframework.web.bind.annotation.*;

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
}
