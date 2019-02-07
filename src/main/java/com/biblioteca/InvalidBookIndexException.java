package com.biblioteca;

public class InvalidBookIndexException extends RuntimeException {
    InvalidBookIndexException(){
        super("Book not found");
    }
}
