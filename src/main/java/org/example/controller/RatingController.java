package org.example.controller;

import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.example.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingRepository ratingRepository;
    private final RatingService ratingService;
    public RatingController(RatingRepository ratingRepository, RatingService ratingService){
        this.ratingRepository = ratingRepository;
        this.ratingService =  ratingService;
    }

    @PostMapping()
    public String createRating(@RequestBody Rating rating){
        return ratingService.createRating(rating);
    }

    @GetMapping()
    public String getAll(){
        return ratingService.getAll();
    }

    @GetMapping("/tours/{id}")
    public String getRatingByTourId(@PathVariable("id") int id){
        return ratingService.getRatingByTourId(id);
    }

    @GetMapping("/{id}")
    public Rating getByRatingId(@PathVariable("id") int id) {
        return ratingService.getRatingById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRating(@PathVariable("id") int id) {
        return ratingService.deleteRating(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRating(@PathVariable("id") int id, @RequestBody Rating updateRating){
        Rating rating = ratingService.updateRating(id, updateRating);

        if (rating == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(rating);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchRating(@PathVariable("id") int id, @RequestBody Rating patchRating){
        Rating rating = ratingService.patchRating(id, patchRating);

        if (rating == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/json")
    public List<Rating> findAllJson(){
        return ratingService.findAllJson();
    }
}
