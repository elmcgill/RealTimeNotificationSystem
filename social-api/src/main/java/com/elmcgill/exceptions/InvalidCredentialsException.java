package com.elmcgill.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message){
        super(message);
    }

    public InvalidCredentialsException(){
        super("Email or password are incorrect");
    }

}
