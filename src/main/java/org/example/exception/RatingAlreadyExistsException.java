package org.example.exception;

public class RatingAlreadyExistsException extends RuntimeException{

    public RatingAlreadyExistsException(String message) {
        super(message);
    }
}
