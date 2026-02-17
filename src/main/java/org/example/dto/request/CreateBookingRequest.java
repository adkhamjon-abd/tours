package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateBookingRequest {

    @NotNull(message = "User id is required")
    @Positive(message = "User id must be greater than 0")
    private int userId;

    @NotNull(message = "Tour id is required")
    @Positive(message = "Tour id must be greater than 0")
    private int tourId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }
}
