package org.example.dto.mapper;

import org.example.dto.request.CreateBookingRequest;
import org.example.dto.request.UpdateBookingRequest;
import org.example.dto.response.BookingResponse;
import org.example.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toEntity(CreateBookingRequest createBookingRequest);

    Booking toEntity(UpdateBookingRequest updateBookingRequest);

    BookingResponse toResponse(Booking booking);
}
