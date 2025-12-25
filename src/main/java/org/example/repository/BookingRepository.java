package org.example.repository;

import org.example.model.Booking;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingRepository {

    private final UserRepository userRepository;

    private final Map<Integer, Booking> bookings = new HashMap<>();
    private int nextId = 2;
    public BookingRepository(UserRepository userRepository){
        bookings.put(1, new Booking(1, 1, 1));
        this.userRepository = userRepository;
    }

    public Booking save(Booking booking){
        int userId = booking.getUserId();
        int tourId = booking.getTourId();

        booking.setId(nextId++);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Optional<Booking> findById(int id) {
        for(Booking current : bookings.values()){
            if (current.getId() == id){
                return Optional.of(current);
            }
        }
        return Optional.empty();
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

    public void deleteById(int id) {
        bookings.entrySet().removeIf(entry -> entry.getValue().getId() == id);
    }

    public void update(Booking booking) {
        bookings.put(booking.getId(), booking);
    }
}

