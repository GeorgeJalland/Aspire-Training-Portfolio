package com.mthree.flooringmastery.service;

public class OrderDoesNotExistException extends Exception {

    public OrderDoesNotExistException(String message) {
        super(message);
    }

    public OrderDoesNotExistException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
