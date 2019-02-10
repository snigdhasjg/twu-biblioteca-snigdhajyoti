package com.biblioteca.exception;

public class InvalidItemNameException extends Exception{
    public InvalidItemNameException(){
        super("Book not found. Wrong name");
    }
}
