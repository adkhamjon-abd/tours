package org.example.exception;

public class UserNotFoundException extends RuntimeException{


    private int id;
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
