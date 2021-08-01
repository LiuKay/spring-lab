package com.kay.example.springretry;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
