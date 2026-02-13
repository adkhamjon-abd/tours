package org.example.exception;

public class TourNotFoundException extends RuntimeException{
    private int id;

    public TourNotFoundException(String message){
        super(message);
    }

    public TourNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

