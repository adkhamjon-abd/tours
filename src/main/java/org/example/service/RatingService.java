package org.example.service;

import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Rating updateRating(int id, Rating updateRating) {
        Rating rating = ratingRepository.findById(id);

        if (rating == null){
            return null;
        }

        rating.setUserId(updateRating.getUserId());
        rating.setTourId(updateRating.getTourId());

        if (updateRating.getScore() > 5 || updateRating.getScore() < 1) {
            System.out.println("score out or range");
            return null;
        }
        rating.setScore(updateRating.getScore());

        ratingRepository.update(rating);
        return rating;
    }

    public Rating patchRating(int id, Rating patchRating) {
        Rating rating = ratingRepository.findById(id);

        if (rating == null) {
            return null;
        }

        if (patchRating.getUserId() > 0){
            rating.setUserId(patchRating.getUserId());
        }

        if (patchRating.getTourId() > 0){
            rating.setTourId(patchRating.getTourId());
        }

        if (patchRating.getScore() > 0){
            rating.setScore(patchRating.getScore());
        }

        ratingRepository.update(rating);
        return rating;
    }

    public List<Rating> findAllJson() {
        return ratingRepository.findAllJson();
    }
}
