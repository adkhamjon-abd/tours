package org.example.service.abstractions;

import org.example.model.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking getBooking(int id);
    List<Booking> getBookingByUserId(int id);
    List<Booking> getAllBookings();
    void deleteBooking(int id);
    Booking updateBooking(int id, Booking booking);
    Booking patchBooking(int id, Booking updateBooking);

}
