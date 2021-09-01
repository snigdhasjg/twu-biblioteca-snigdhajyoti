package com.biblioteca.exception;

public class InvalidItemNameException extends Exception {
    public InvalidItemNameException() {
        super("Item not found. Wrong name");
    }
}
