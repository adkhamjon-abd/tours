package org.example.controller;

import org.example.model.Tour;
import org.example.response.ApiResponse;
import org.example.service.impl.TourServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourServiceImpl tourService;

    public TourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Tour>> createTour(@RequestBody Tour tour) {
        Tour created = tourService.createTour(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tour>> getById(@PathVariable("id") int id) {
        Tour tour = tourService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Tour>>> getAll() {
        List<Tour> tours = tourService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(tours));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTour(@PathVariable("id") int id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Tour>> updateTour(
            @PathVariable("id") int id,
            @RequestBody Tour updateTour
    ) {
        Tour tour = tourService.updateTour(id, updateTour);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Tour>> patchTour(
            @PathVariable("id") int id,
            @RequestBody Tour updateTour
    ) {
        Tour tour = tourService.patchTour(id, updateTour);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }
}
