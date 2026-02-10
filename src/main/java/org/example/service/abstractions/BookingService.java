package org.example.service.abstractions;

import org.example.dto.request.CreateBookingRequest;
import org.example.dto.request.UpdateBookingRequest;
import org.example.dto.response.BookingResponse;
import org.example.model.Booking;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(CreateBookingRequest booking);
    BookingResponse getBooking(int id);
    List<BookingResponse> getBookingByUserId(int id);
    List<BookingResponse> getAllBookings();
    void deleteBooking(int id);
    BookingResponse updateBooking(int id, UpdateBookingRequest booking);
    BookingResponse patchBooking(int id, UpdateBookingRequest updateBooking);

}
