package com.tech_raj.exception;

public class InvalidCredentialsException extends RuntimeException  {

    public InvalidCredentialsException(String message){
        super(message);
    }
}
