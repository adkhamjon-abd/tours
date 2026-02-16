package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateRatingRequest {

    @NotNull(message = "Company id is required")
    @Positive(message = "Company id must be positive value")
    private Integer tourId;
    @NotNull(message = "User id is required")
    @Positive(message = "User id must be positive value")
    private Integer userId;
    @NotNull(message = "Score is required")
    @Positive(message = "Score must be positive")
    private Integer score;



    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
