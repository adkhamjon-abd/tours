package org.example.service;

import org.example.exception.BookingAlreadyExistsException;
import org.example.exception.BookingNotFoundException;
import org.example.exception.InvalidBookingDataException;
import org.example.exception.UserNotFoundException;
import org.example.model.Booking;
import org.example.model.Tour;
import org.example.model.User;
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


    public Booking createBooking(Booking booking) {

        User user = userRepository.findById(booking.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        Tour tour = tourRepository.findById(booking.getTourId());

        if (tour == null) {
            throw new UserNotFoundException("Failed to create booking");
        }

        boolean booked = bookingRepository.findByUserId(user.getId()).stream().anyMatch(b -> b.getTourId() == booking.getTourId());

        if (booked){
            throw new BookingAlreadyExistsException("Booking already exists");
        }

        Booking result = bookingRepository.save(booking);

        return result;

    }

    public Booking getBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with such id does not exist");
        }
        return booking;
    }

    public List<Booking> getBookingByUserId(int id) {
        User user  = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));
        List<Booking> bookings = bookingRepository.findByUserId(id);

        return bookings;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings;
    }

    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with such Id does not exist");
        }
        bookingRepository.deleteById(id);
    }

    public Booking updateBooking(int id, Booking booking) {
        if (booking.getUserId() <= 0 || booking.getTourId() <= 0) {
            throw new InvalidBookingDataException("userId and tourId must be provided and > 0");
        }
        Booking existing = bookingRepository.findById(id);

        if (existing == null){
            throw new BookingNotFoundException("There is no booking with such Id");
        }

        existing.setUserId(booking.getUserId());
        existing.setTourId(booking.getTourId());
        bookingRepository.update(existing);

        return existing;
    }

    public Booking patchBooking(int id, Booking updateBooking) {


        Booking existing = bookingRepository.findById(id);

        if (existing == null){ throw new BookingNotFoundException("Booking with such Id does not exist"); }


        if (updateBooking.getUserId() > 0) {
            userRepository.findById(updateBooking.getUserId()).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));
            existing.setUserId(updateBooking.getUserId());
        }

        if (updateBooking.getTourId() > 0) {
            if (tourRepository.findById(updateBooking.getTourId()) == null) {
                throw new UserNotFoundException("Tour with such Id does not exist");
            }
            existing.setTourId(updateBooking.getTourId());
        }

        bookingRepository.update(existing);
        return existing;
    }
}
