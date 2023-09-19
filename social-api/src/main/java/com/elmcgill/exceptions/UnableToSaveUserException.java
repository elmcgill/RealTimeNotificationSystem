package com.elmcgill.exceptions;

public class UnableToSaveUserException extends RuntimeException{

    public UnableToSaveUserException(){
        super("Unable to save the user object passed");
    }

    public UnableToSaveUserException(String message){
        super(message);
    }

}
