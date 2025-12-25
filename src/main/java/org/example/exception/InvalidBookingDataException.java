package org.example.exception;

public class InvalidBookingDataException extends RuntimeException{
    public InvalidBookingDataException(String message) {
        super(message);
    }
}
