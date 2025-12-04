package org.example.controller;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    public BookingController(BookingRepository bookingRepository,
                             UserRepository userRepository,
                             TourRepository tourRepository){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }

    @PostMapping("/book")
    public String creatBooking(@RequestBody Booking booking){

        Integer userId = userRepository.findById(booking.getUserId()).getId();

        if (userId == null){
            return "No such user with this id";
        }

        if (tourRepository.findById(booking.getTourId()) == null){
            return "Tour with such id does not exist";
        }
        bookingRepository.save(booking);

        return "Booking was successful for tour " + tourRepository.findById(booking.getTourId()).getName() + " with id: "
                + booking.getTourId() + ". The Person who booked is: " + userRepository.findById(booking.getUserId()).getUsername();
    }
}
