package org.example.controller;

import org.example.model.Booking;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserController userController;
    private final TourRepository tourRepository;

    public BookingController(BookingRepository bookingRepository,
                             UserController userController,
                             TourRepository tourRepository){
        this.bookingRepository = bookingRepository;
        this.userController = userController;
        this.tourRepository = tourRepository;
    }

    @PostMapping("book/{tourId}")
    public String creatBooking(@RequestHeader("Authorization") String token,
                               @PathVariable("tourId") int tourId){

        Integer userId = userController.getUserIdFromToken(token);

        if (userId == null){
            return "No token";
        }

        if (tourRepository.findById(tourId) == null){
            return "Tour with such id does not exist";
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setTourId(tourId);
        bookingRepository.save(booking);

        return "Booking was successful for tour " + tourRepository.findById(tourId).getName() + " with id: "
                + tourId;
    }
}
