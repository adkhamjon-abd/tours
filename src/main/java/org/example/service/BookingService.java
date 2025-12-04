package org.example.service;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }


    public String createBooking(Booking booking) {

        Integer userId = userRepository.findById(booking.getUserId()).getId();

        if (userId == null) {
            return "No such user with this id";
        }

        if (tourRepository.findById(booking.getTourId()) == null) {
            return "Tour with such id does not exist";
        }

        String result = bookingRepository.save(booking);

        if (result.startsWith("The User has already booked")) {
            return result;
        }

        return "Booking was successful for tour " + tourRepository.findById(booking.getTourId()).getName() + " with id: "
                + booking.getTourId() + ". The Person who booked is: " + userRepository.findById(booking.getUserId()).getUsername();
    }

    public List<Booking> getBooking(int id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingByUserId(int id) {
        return bookingRepository.findByUserId(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String deleteBooking(int id) {
        return bookingRepository.deleteById(id);
    }
}
