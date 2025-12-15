package org.example.exception;

public class TourAlreadyExistsException extends RuntimeException{

    public TourAlreadyExistsException(String message) {
        super(message);
    }
}
