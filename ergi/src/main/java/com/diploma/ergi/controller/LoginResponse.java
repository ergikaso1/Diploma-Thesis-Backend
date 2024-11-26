package com.diploma.ergi.controller;

public class LoginResponse {
    private String message;
    private int errorCode;

    public LoginResponse() {
    }

    public LoginResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

