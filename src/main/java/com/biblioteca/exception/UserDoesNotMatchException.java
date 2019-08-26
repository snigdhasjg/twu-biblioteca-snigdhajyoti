package com.biblioteca.exception;

public class UserDoesNotMatchException extends Exception {
    public UserDoesNotMatchException() {
        super("User mismatch");
    }
}
