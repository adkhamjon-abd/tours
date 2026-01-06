package org.example.dto.mapper;

import org.example.dto.BookingDTO;
import org.example.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO bookingToBookingDTO(Booking booking);
}
