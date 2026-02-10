package org.example.exception;

public class CompanyNotFoundException extends RuntimeException{
    int id;

    public CompanyNotFoundException(String message){
        super(message);
    }

    public CompanyNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
