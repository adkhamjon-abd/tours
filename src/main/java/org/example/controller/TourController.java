package org.example.controller;

import org.example.dto.request.CreateTourRequest;
import org.example.dto.request.UpdateTourRequest;
import org.example.dto.response.TourResponse;
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
    public ResponseEntity<ApiResponse<TourResponse>> createTour(@RequestBody CreateTourRequest tourRequest) {
        TourResponse created = tourService.createTour(tourRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TourResponse>> getById(@PathVariable("id") int id) {
        TourResponse tour = tourService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TourResponse>>> getAll() {
        List<TourResponse> tours = tourService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(tours));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTour(@PathVariable("id") int id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TourResponse>> updateTour(
            @PathVariable("id") int id,
            @RequestBody UpdateTourRequest updateTourRequest
    ) {
        TourResponse tour = tourService.updateTour(id, updateTourRequest);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TourResponse>> patchTour(
            @PathVariable("id") int id,
            @RequestBody UpdateTourRequest updateTour
    ) {
        TourResponse tour = tourService.patchTour(id, updateTour);
        return ResponseEntity.ok(new ApiResponse<>(tour));
    }
}
