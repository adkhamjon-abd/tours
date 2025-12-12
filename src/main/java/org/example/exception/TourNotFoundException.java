package org.example.exception;

public class TourNotFoundException extends RuntimeException{

    public TourNotFoundException(String message){
        super(message);
    }
}

