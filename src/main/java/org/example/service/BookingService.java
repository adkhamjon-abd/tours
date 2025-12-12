package org.example.service;

import org.example.exception.BookingNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.model.Booking;
import org.example.model.Tour;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public ResponseEntity<ApiResponse<Booking>> createBooking(Booking booking) {

        User user = userRepository.findById(booking.getUserId());

        if (user == null) {
            throw new UserNotFoundException("User with such id does not exist");
        }

        Tour tour = tourRepository.findById(booking.getTourId());

        if (tour == null) {
            throw new UserNotFoundException("Failed to create booking");
        }

        boolean booked = bookingRepository.findByUserId(user.getId()).stream().anyMatch(b -> b.getTourId() == booking.getTourId());

        if (booked){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("The User has already booked this tour"));
        }

        Booking result = bookingRepository.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(result));

    }

    public ResponseEntity<ApiResponse<Booking>> getBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Booking with such id does not exist"));
        }
        return ResponseEntity.ok(new ApiResponse<>(booking));
    }

    public ResponseEntity<ApiResponse<List<Booking>>> getBookingByUserId(int id) {
        User user  = userRepository.findById(id);
        if (user == null){
            throw new UserNotFoundException("User with such id does not exist");
        }
        List<Booking> bookings = bookingRepository.findByUserId(id);

        return ResponseEntity.ok(new ApiResponse<>(bookings));
    }

    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(bookings));
    }

    public ResponseEntity<Void> deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with such Id does not exist");
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ApiResponse<Booking>> updateBooking(int id, Booking booking) {
        if (booking.getUserId() <= 0 || booking.getTourId() <= 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("userId and tourId must be provided and > 0"));
        }
        Booking existing = bookingRepository.findById(id);

        if (existing == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("There is no booking with such Id"));
        }

        existing.setUserId(booking.getUserId());
        existing.setTourId(booking.getTourId());
        bookingRepository.update(existing);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(existing));
    }

    public ResponseEntity<ApiResponse<Booking>> patchBooking(int id, Booking updateBooking) {


        Booking existing = bookingRepository.findById(id);

        if (existing == null){ throw new BookingNotFoundException("Booking with such Id does not exist"); }


        if (updateBooking.getUserId() > 0) {
            if (userRepository.findById(updateBooking.getUserId()) == null) {
                throw new UserNotFoundException("User with such Id does not exist");
            }
            existing.setUserId(updateBooking.getUserId());
        }

        if (updateBooking.getTourId() > 0) {
            if (tourRepository.findById(updateBooking.getTourId()) == null) {
                throw new UserNotFoundException("Tour with such Id does not exist");
            }
            existing.setTourId(updateBooking.getTourId());
        }

        bookingRepository.update(existing);
        return ResponseEntity.ok(new ApiResponse<>(existing));
    }
}
