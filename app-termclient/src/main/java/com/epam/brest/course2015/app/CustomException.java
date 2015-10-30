package com.epam.brest.course2015.app;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}