package org.example.exception;

public class BookingNotFoundException extends RuntimeException{
    private int id;

    public BookingNotFoundException(String message){
        super(message);
    }

    public BookingNotFoundException(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
