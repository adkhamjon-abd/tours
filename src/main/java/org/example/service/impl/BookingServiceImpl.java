package org.example.service.impl;

import org.example.dto.BookingDTO;
import org.example.dto.mapper.BookingMapper;
import org.example.exception.*;
import org.example.model.Booking;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.service.abstractions.BookingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              TourRepository tourRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.bookingMapper = bookingMapper;
    }


    public BookingDTO createBooking(Booking booking) {
        //Check user
        User user = userRepository
                .findById(booking.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        //Check tour
        tourRepository.findById(booking.getTourId()).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );

        boolean booked =
                getBookingByUserId(user.getId())
                .stream()
                .anyMatch(b -> b.getTourId() == booking.getTourId());

        if (booked){
            throw new BookingAlreadyExistsException("Booking already exists");
        }

        Booking result = bookingRepository.save(booking);

        return bookingMapper.bookingToBookingDTO(result);

    }

    public BookingDTO getBooking(int id) {
        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with such id does not exist"));

        return bookingMapper.bookingToBookingDTO(booking);
    }

    public List<BookingDTO> getBookingByUserId(int id) {
        User user  = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> idBookings = new ArrayList<>();
        for(Booking current : bookings){
            if (current.getUserId() == id){
                idBookings.add(current);
            }
        }
        return idBookings.stream().map(bookingMapper::bookingToBookingDTO).toList();
    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::bookingToBookingDTO).toList();
    }

    public void deleteBooking(int id) {
        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with such id does not exist"));
        bookingRepository.deleteById(id);
    }

    public BookingDTO updateBooking(int id, Booking booking) {
        if (booking.getUserId() <= 0 || booking.getTourId() <= 0) {
            throw new InvalidBookingDataException("userId and tourId must be provided and > 0");
        }
        Booking existing = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with such id does not exist"));

        existing.setUserId(booking.getUserId());
        existing.setTourId(booking.getTourId());
        bookingRepository.update(existing);

        return bookingMapper.bookingToBookingDTO(existing);
    }

    public BookingDTO patchBooking(int id, Booking updateBooking) {


        Booking existing = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with such id does not exist"));


        if (updateBooking.getUserId() > 0) {
            userRepository
                    .findById(updateBooking.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));
            existing.setUserId(updateBooking.getUserId());
        }

        if (updateBooking.getTourId() > 0) {
            tourRepository
                    .findById(updateBooking.getTourId())
                    .orElseThrow(() -> new TourNotFoundException("Tour with such Id does not exist"));

            existing.setTourId(updateBooking.getTourId());
        }

        bookingRepository.update(existing);
        return bookingMapper.bookingToBookingDTO(existing);
    }
}
