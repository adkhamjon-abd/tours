package org.example.controller;

import org.example.model.Booking;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TourRepository;
import org.example.repository.UserRepository;
import org.example.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final BookingService bookingService;

    public BookingController(BookingRepository bookingRepository,
                             UserRepository userRepository,
                             TourRepository tourRepository,
                             BookingService bookingService){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking){
        String bookingResult = bookingService.createBooking(booking);
        if (bookingResult.startsWith("Booking was successful")){
            return ResponseEntity.status(HttpStatus.CREATED).body("Booking created");
        }
        if (bookingResult.startsWith("No such user")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such id does not exist");
        }
        if (bookingResult.startsWith("Tour with such")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tour with such id does not exist");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create booking");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") int id) {
        Booking booking = bookingService.getBooking(id);
        if (booking == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking with such id does not exist");
        }
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable("id") int id) {
        User user  = userRepository.findById(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such id does not exist");
        }
        List<Booking> bookings =  bookingService.getBookingByUserId(id);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        return bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.updateBooking(id, updateBooking);
        if (booking == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(booking);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Booking> patchBooking(@PathVariable("id") int id, @RequestBody Booking updateBooking) {
        Booking booking = bookingService.patchUser(id, updateBooking);

        if (booking == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(booking);
    }
}
