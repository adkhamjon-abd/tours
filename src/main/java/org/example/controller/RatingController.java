package org.example.controller;

import org.example.model.Rating;
import org.example.response.ApiResponse;
import org.example.service.impl.RatingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Rating>> createRating(@RequestBody Rating rating) {
        Rating newRating = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(newRating));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Rating>>> getAll() {
        List<Rating> ratings = ratingService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(ratings));
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<ApiResponse<Double>> getAverageRatingByTourId(@PathVariable("id") int id) {
        Double average = ratingService.getAverageRatingByTourId(id);
        return ResponseEntity.ok(new ApiResponse<>(average));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Rating>> getRating(@PathVariable("id") int id) {
        Rating rating = ratingService.getRatingById(id);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable("id") int id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Rating>> updateRating(@PathVariable("id") int id, @RequestBody Rating updateRating) {
        Rating rating = ratingService.updateRating(id, updateRating);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Rating>> patchRating(@PathVariable("id") int id, @RequestBody Rating patchRating) {
        Rating rating = ratingService.patchRating(id, patchRating);
        return ResponseEntity.ok(new ApiResponse<>(rating));
    }
}
