package com.biblioteca.exception;

public class NotABookLibraryException extends Exception {
    public NotABookLibraryException() {
        super("This Library is not a book library");
    }
}
