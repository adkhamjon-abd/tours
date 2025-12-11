package org.example.service;

import org.example.model.Booking;
import org.example.model.Tour;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
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


    public ResponseEntity<?> createBooking(Booking booking) {

        User user = userRepository.findById(booking.getUserId());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such id does not exist");
        }

        Tour tour = tourRepository.findById(booking.getTourId());

        if ( tour == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create booking");
        }

        String result = bookingRepository.save(booking);

        if (result.startsWith("The User has already booked")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The User has already booked this tour");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created");

    }

    public ResponseEntity<?> getBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking with such id does not exist");
        }
        return ResponseEntity.ok(booking);
    }

    public ResponseEntity<?> getBookingByUserId(int id) {
        User user  = userRepository.findById(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such id does not exist");
        }
        List<Booking> bookings = bookingRepository.findByUserId(id);

        return ResponseEntity.ok(bookings);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public ResponseEntity<?> deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> updateBooking(int id, Booking booking) {
        if (booking.getUserId() <= 0 || booking.getTourId() <= 0) {
            return ResponseEntity.badRequest().body("userId and tourId must be provided and > 0");
        }
        Booking existing = bookingRepository.findById(id);

        if (existing == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setUserId(booking.getUserId());
        existing.setTourId(booking.getTourId());
        bookingRepository.update(existing);

        return ResponseEntity.status(HttpStatus.OK).body(existing);
    }

    public ResponseEntity<?> patchUser(int id, Booking updateBooking) {

        //check if updateBooking is ok
        if (updateBooking.getUserId() <= 0) {
            return ResponseEntity.badRequest().body("userId must be greater than 0");
        }
        if (updateBooking.getTourId() <= 0) {
            return ResponseEntity.badRequest().body("tourId must be greater than 0");
        }

        Booking existing = bookingRepository.findById(id);

        if (existing == null) return ResponseEntity.notFound().build();

        if (userRepository.findById(updateBooking.getUserId()) == null) {
            return ResponseEntity.notFound().build();
        }
        if (updateBooking.getUserId() >=0) {
            existing.setUserId(updateBooking.getUserId());
        }

        if (updateBooking.getTourId() >= 0) {
            existing.setTourId(updateBooking.getTourId());;
        }

        bookingRepository.update(existing);
        return ResponseEntity.ok(existing);
    }
}
