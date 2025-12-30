package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int tourId;
    private int userId;
    private int score;


    public Rating(){}

    public Rating(int id, int tourId, int userId, int score){
        this.id = id;
        this.tourId = tourId;
        this.userId = userId;
        this.score = score;
    }
    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
