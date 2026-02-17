package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.request.CreateRatingRequest;
import org.example.dto.request.UpdateRatingRequest;
import org.example.dto.response.RatingResponse;
import org.example.model.Rating;
import org.example.response.ApiResponse;
import org.example.service.abstractions.RatingService;
import org.example.service.impl.RatingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<RatingResponse>> createRating(@RequestBody @Valid CreateRatingRequest rating) {
        RatingResponse newRating = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(newRating));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RatingResponse>>> getAll() {
        List<RatingResponse> ratings = ratingService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(ratings));
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<ApiResponse<Double>> getAverageRatingByTourId(@PathVariable("id") int id) {
        Double average = ratingService.getAverageRatingByTourId(id);
        return ResponseEntity.ok(new ApiResponse<>(average));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponse>> getRating(@PathVariable("id") int id) {
        RatingResponse rating = ratingService.getRatingById(id);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable("id") int id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponse>> updateRating(@PathVariable("id") int id, @RequestBody UpdateRatingRequest updateRating) {
        RatingResponse rating = ratingService.updateRating(id, updateRating);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponse>> patchRating(@PathVariable("id") int id, @RequestBody UpdateRatingRequest patchRating) {
        RatingResponse rating = ratingService.patchRating(id, patchRating);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }
}
