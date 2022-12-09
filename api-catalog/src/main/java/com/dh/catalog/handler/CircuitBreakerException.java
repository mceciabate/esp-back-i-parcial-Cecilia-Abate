package com.dh.catalog.handler;

public class CircuitBreakerException extends Exception{
    public CircuitBreakerException(String message) {
        super(message);
    }
}
