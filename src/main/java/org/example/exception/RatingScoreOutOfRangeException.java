package org.example.exception;

public class RatingScoreOutOfRangeException extends RuntimeException{

    public RatingScoreOutOfRangeException(String message) {
        super(message);
    }
}
