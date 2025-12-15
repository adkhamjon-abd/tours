package org.example.service;

import org.example.exception.RatingAlreadyExistsException;
import org.example.exception.RatingNotFoundException;
import org.example.exception.RatingScoreOutOfRangeException;
import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    private Double fingAverageRatingByTourId(int id) {
        double totalScore = 0;
        double numberOfScores = 0;
        List<Rating> ratings = ratingRepository.findAll();
        for (Rating rating : ratings){
            if (rating.getTourId() == id){
                totalScore += rating.getScore();
                numberOfScores += 1;
            }
        }

        return totalScore / numberOfScores;
    }

    public Rating createRating(Rating rating) {
        Rating existing = ratingRepository.findById(rating.getId());

        if (existing != null) {
            throw new RatingAlreadyExistsException("Rating with such id already exists");
        }
        return ratingRepository.save(rating);
    }

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public Double getAverageRatingByTourId(int id) {
        return fingAverageRatingByTourId(id);
    }

    public Rating getRatingById(int id) {
        Rating rating = ratingRepository.findById(id);

        if (rating == null){
            throw new RatingNotFoundException("Rating with such id does not exist");
        }

        return ratingRepository.findById(id);
    }

    public void deleteRating(int id) {
        Rating existing = ratingRepository.findById(id);
        if (existing == null) {
            throw new RatingNotFoundException("Rating with such id does not exist");
        }
        ratingRepository.deleteById(id);
    }

    public Rating updateRating(int id, Rating updateRating) {
        Rating rating = ratingRepository.findById(id);

        if (rating == null){
            throw new RatingNotFoundException("Rating with such id does not exist");
        }

        rating.setUserId(updateRating.getUserId());
        rating.setTourId(updateRating.getTourId());

        if (updateRating.getScore() > 5 || updateRating.getScore() < 1) {
            throw new RatingScoreOutOfRangeException("Score must be between 1 and 5");
        }
        rating.setScore(updateRating.getScore());

        ratingRepository.update(rating);
        return rating;
    }

    public Rating patchRating(int id, Rating patchRating) {
        Rating rating = ratingRepository.findById(id);

        if (rating == null) {
            throw new RatingNotFoundException("Rating with such id does not exist");
        }

        if (patchRating.getUserId() > 0){

            rating.setUserId(patchRating.getUserId());
        }

        if (patchRating.getTourId() > 0){
            rating.setTourId(patchRating.getTourId());
        }

        if (patchRating.getScore() > 0){
            if (patchRating.getScore() > 5 || patchRating.getScore() < 1) {
                throw new RatingScoreOutOfRangeException("Score must be between 1 and 5");
            }
            rating.setScore(patchRating.getScore());
        }

        ratingRepository.update(rating);
        return rating;
    }
}
