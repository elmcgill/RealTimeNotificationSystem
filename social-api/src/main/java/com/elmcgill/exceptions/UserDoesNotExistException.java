package com.elmcgill.exceptions;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(){
        super("The specified user does not exist");
    }

    public UserDoesNotExistException(String message){
        super(message);
    }

}
