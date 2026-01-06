package org.example.service.abstractions;

import org.example.dto.RatingDTO;
import org.example.exception.RatingAlreadyExistsException;
import org.example.exception.TourNotFoundException;
import org.example.model.Rating;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(Rating rating);
    List<RatingDTO> getAll();
    Double getAverageRatingByTourId(int id);
    RatingDTO getRatingById(int id);
    void deleteRating(int id);
    RatingDTO updateRating(int id, Rating updateRating);
    RatingDTO patchRating(int id, Rating patchRating);

}
