package com.biblioteca;

public class InvalidBookNameException extends Exception{
    public InvalidBookNameException(){
        super("Book not found. Wrong name");
    }
}
