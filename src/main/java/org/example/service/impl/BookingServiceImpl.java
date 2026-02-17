package org.example.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.example.dto.mapper.BookingMapper;
import org.example.dto.response.BookingResponse;
import org.example.dto.request.CreateBookingRequest;
import org.example.dto.request.UpdateBookingRequest;
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

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest) {

        Booking booking = bookingMapper.toEntity(createBookingRequest);
        //Check user
        User user = userRepository
                .findById(booking.getUserId())
                .orElseThrow(() -> new UserNotFoundException(booking.getUserId()));

        //Check tour
        tourRepository.findById(booking.getTourId()).orElseThrow(() ->
                new TourNotFoundException(booking.getTourId())
        );

        boolean booked =
                getBookingByUserId(user.getId())
                .stream()
                .anyMatch(b -> b.getTourId() == booking.getTourId());

        if (booked){
            throw new BookingAlreadyExistsException("Booking already exists");
        }

        Booking result = bookingRepository.save(booking);

        return bookingMapper.toResponse(result);

    }

    @Transactional(readOnly = true)
    public BookingResponse getBooking(int id) {
        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        return bookingMapper.toResponse(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingByUserId(int id) {
        User user  = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> idBookings = new ArrayList<>();
        for(Booking current : bookings){
            if (current.getUserId() == id){
                idBookings.add(current);
            }
        }
        return idBookings.stream().map(bookingMapper::toResponse).toList();
    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toResponse).toList();
    }

    @Transactional
    public void deleteBooking(int id) {
        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
        bookingRepository.deleteById(id);
    }

    @Transactional
    public BookingResponse updateBooking(int id, UpdateBookingRequest booking) {
        if (booking.getUserId() <= 0 || booking.getTourId() <= 0) {
            throw new InvalidBookingDataException("userId and tourId must be provided and > 0");
        }
        Booking existing = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        existing.setUserId(booking.getUserId());
        existing.setTourId(booking.getTourId());
        bookingRepository.update(existing);

        return bookingMapper.toResponse(existing);
    }

    @Transactional
    public BookingResponse patchBooking(int id, UpdateBookingRequest updateBooking) {


        Booking existing = bookingRepository
                .findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));


        if (updateBooking.getUserId() > 0) {
            userRepository
                    .findById(updateBooking.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(id));
            existing.setUserId(updateBooking.getUserId());
        }

        if (updateBooking.getTourId() > 0) {
            tourRepository
                    .findById(updateBooking.getTourId())
                    .orElseThrow(() -> new TourNotFoundException(updateBooking.getTourId()));

            existing.setTourId(updateBooking.getTourId());
        }

        bookingRepository.update(existing);
        return bookingMapper.toResponse(existing);
    }
}
