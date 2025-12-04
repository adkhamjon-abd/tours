package org.example.controller;

import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.example.service.RatingService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getRatingByTourId(@PathVariable("id") int id){
        return ratingService.getRatingByTourId(id);
    }

}
