package org.example.service.impl;

import org.example.dto.mapper.RatingMapper;
import org.example.dto.request.CreateRatingRequest;
import org.example.dto.request.UpdateRatingRequest;
import org.example.dto.response.RatingResponse;
import org.example.exception.RatingAlreadyExistsException;
import org.example.exception.RatingNotFoundException;
import org.example.exception.RatingScoreOutOfRangeException;
import org.example.exception.TourNotFoundException;
import org.example.model.Rating;
import org.example.repository.RatingRepository;
import org.example.repository.TourRepository;
import org.example.service.abstractions.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private TourRepository tourRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, TourRepository tourRepository, RatingMapper ratingMapper){
        this.ratingRepository = ratingRepository;
        this.tourRepository = tourRepository;
        this.ratingMapper = ratingMapper;
    }

    private Double findAverageRatingByTourId(int id) {
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

    public RatingResponse createRating(CreateRatingRequest createRatingRequest) {
        Rating rating = ratingMapper.toEntity(createRatingRequest);

        ratingRepository.findById(rating.getId())
                .ifPresent(r -> {
                    throw new RatingAlreadyExistsException(
                            "Rating with such id already exists"
                    );
                });
        tourRepository
                .findById(rating.getTourId())
                .orElseThrow(() -> new TourNotFoundException(rating.getTourId()));
        return ratingMapper.toResponse(ratingRepository.save(rating));
    }

    public List<RatingResponse> getAll() {
        return ratingRepository.findAll().stream().map(ratingMapper::toResponse).toList();
    }

    public Double getAverageRatingByTourId(int id) {
        return findAverageRatingByTourId(id);
    }

    public RatingResponse getRatingById(int id) {
        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));

        return ratingMapper.toResponse(rating);
    }

    public void deleteRating(int id) {
        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow(() -> new RatingAlreadyExistsException("Rating with such id does not exist"));
        ratingRepository.deleteById(id);
    }

    public RatingResponse updateRating(int id, UpdateRatingRequest updateRating) {
        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow((()-> new RatingNotFoundException(id)));


        rating.setUserId(updateRating.getUserId());
        rating.setTourId(updateRating.getTourId());

        if (updateRating.getScore() > 5 || updateRating.getScore() < 1) {
            throw new RatingScoreOutOfRangeException("Score must be between 1 and 5");
        }
        rating.setScore(updateRating.getScore());

        ratingRepository.update(rating);
        return ratingMapper.toResponse(rating);
    }

    public RatingResponse patchRating(int id, UpdateRatingRequest patchRating) {
        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));

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
        return ratingMapper.toResponse(rating);
    }
}
