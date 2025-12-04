package org.example.controller;

import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingRepository ratingRepository;
    public RatingController(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @PostMapping()
    public String createRating(@RequestBody Rating rating){
        return ratingRepository.save(rating);

    }

    @GetMapping()
    public String getAll(){
        return ratingRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getRatingByTourId(@PathVariable("id") int id){
        return ratingRepository.fingAverageRatingByTourId(id);
    }

}
