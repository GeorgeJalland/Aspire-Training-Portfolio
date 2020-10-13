package com.mthree.bullsandcows.service;

public class GameDoesNotExistException extends Exception {

    public GameDoesNotExistException(String message) {
        super(message);
    }

    public GameDoesNotExistException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
