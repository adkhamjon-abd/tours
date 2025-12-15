package org.example.repository;

import org.example.model.Rating;
import org.example.model.Tour;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RatingRepository {

    private final Map<Integer, Rating> ratings = new HashMap<>();
    private int nextId = 6;

    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    public RatingRepository(TourRepository tourRepository, UserRepository userRepository) {
        ratings.put(1, new Rating(1, 1, 1, 4));
        ratings.put(2, new Rating(2, 2, 1, 3));
        ratings.put(3, new Rating(3, 3, 1, 4));
        ratings.put(4, new Rating(4, 4, 1, 5));
        ratings.put(5, new Rating(5, 5, 1, 5));

        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }

    public Rating save(Rating rating){

        for (Map.Entry<Integer, Rating> existingEntry : ratings.entrySet()){
            Integer key = existingEntry.getKey();
            Rating value = existingEntry.getValue();

            //When user tries to create Rating with same id then score is updated
            if (value.getTourId() == rating.getTourId() && value.getUserId() == rating.getUserId()){
                value.setScore(rating.getScore());
                ratings.put(key, value);
                return value;
            }
        }
        rating.setId(nextId++);
        ratings.put(rating.getId(), rating);
        return rating;
    }

    public List<Rating> findAll(){
        return new ArrayList<>(ratings.values());
    }

    public String fingAverageRatingByTourId(int id) {
        double totalScore = 0;
        double numberOfScores = 0;
        for (Rating rating : ratings.values()){
            if (rating.getTourId() == id){
                totalScore += rating.getScore();
                numberOfScores += 1;
            }
        }

        double avarege = totalScore / numberOfScores;

        return String.valueOf(avarege);
    }

    public Rating findById(int id) {
        return ratings.values().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);

    }

    public void deleteById(int id) {
        ratings.values().removeIf(rating -> rating.getId() == id);
    }

    public void update(Rating updateRating){
        ratings.put(updateRating.getId(), updateRating);
    }
}
