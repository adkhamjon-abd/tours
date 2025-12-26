package org.example.service.abstractions;

import org.example.exception.RatingAlreadyExistsException;
import org.example.exception.TourNotFoundException;
import org.example.model.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAll();
    Double getAverageRatingByTourId(int id);
    Rating getRatingById(int id);
    void deleteRating(int id);
    Rating updateRating(int id, Rating updateRating);
    Rating patchRating(int id, Rating patchRating);

}
