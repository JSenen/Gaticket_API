package com.juansenen.gaticket.exception;

public class EntityNotFound extends Exception{
    public EntityNotFound(){

        super("User not found");
    }
    public EntityNotFound(String message) {

        super(message);
    }
}
