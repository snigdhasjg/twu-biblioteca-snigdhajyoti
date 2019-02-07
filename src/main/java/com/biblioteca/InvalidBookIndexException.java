package com.biblioteca;

public class InvalidBookIndexException extends Exception {
    public InvalidBookIndexException(){
        super("Book not found. Wrong index");
    }
}
