package org.example.repository;

import org.example.model.Booking;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository {

    private final UserRepository userRepository;

    private final Map<Integer, Booking> bookings = new HashMap<>();

    public BookingRepository(UserRepository userRepository){
        bookings.put(1, new Booking(1, 1, 1));
        this.userRepository = userRepository;
    }

    public String save(Booking booking){
        int userId = booking.getUserId();
        int tourId = booking.getTourId();

        for(Booking current : bookings.values()){
            if (current.getTourId() == tourId && current.getUserId() == userId){
                return "The User has already booked this tour";
            }
        }
        int id = bookings.size();
        booking.setId(id);
        bookings.put(id, booking);
        return "Booking: " + booking.getId() + " for UserName: " + userRepository.findById(booking.getUserId()).getUsername();
    }

    public Booking findById(int id) {
        for(Booking current : bookings.values()){
            if (current.getId() == id){
                return current;
            }
        }
        return null;
    }

    public List<Booking> findByUserId(int id) {
        List<Booking> idBookings = new ArrayList<>();
        for(Booking current : bookings.values()){
            if (current.getUserId() == id){
                idBookings.add(current);
            }
        }
        return idBookings;
    }

    public List<Booking> findAll(){
        List<Booking> idBookings = new ArrayList<>();
        idBookings.addAll(bookings.values());
        return idBookings;
    }

    public String deleteById(int id) {
        bookings.entrySet().removeIf(entry -> entry.getValue().getId() == id);
        return "Deleted";
    }
}

