package com.tech_raj.exception;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(String message){
        super(message);
    }
}
