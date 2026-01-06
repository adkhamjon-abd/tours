package org.example.service.abstractions;

import org.example.dto.BookingDTO;
import org.example.model.Booking;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(Booking booking);
    BookingDTO getBooking(int id);
    List<BookingDTO> getBookingByUserId(int id);
    List<BookingDTO> getAllBookings();
    void deleteBooking(int id);
    BookingDTO updateBooking(int id, Booking booking);
    BookingDTO patchBooking(int id, Booking updateBooking);

}
