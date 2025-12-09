package org.example.service;

import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }


    public String createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public String getAll() {
        return ratingRepository.findAll();
    }

    public String getRatingByTourId(int id) {
        return ratingRepository.fingAverageRatingByTourId(id);
    }

    public Rating getRatingById(int id) {
        return ratingRepository.findById(id);
    }

    public String deleteRating(int id) {
        Rating existing = ratingRepository.findById(id);
        if (existing == null) {
            return "User does not exist";
        }
        return ratingRepository.deleteById(id);
    }
}
