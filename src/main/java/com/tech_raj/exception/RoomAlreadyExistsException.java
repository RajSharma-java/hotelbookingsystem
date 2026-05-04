package com.tech_raj.exception;

public class RoomAlreadyExistsException extends RuntimeException{

    public RoomAlreadyExistsException(String message){
        super(message);
    }
}
