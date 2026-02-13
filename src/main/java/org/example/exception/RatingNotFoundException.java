package org.example.exception;

public class RatingNotFoundException extends RuntimeException{
    private int id;

    public RatingNotFoundException(String message) {
        super(message);
    }

    public RatingNotFoundException(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
