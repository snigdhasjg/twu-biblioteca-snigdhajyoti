package com.biblioteca.exception;

public class NotAMovieLibraryException extends Exception {
    public NotAMovieLibraryException() {
        super("This Library is not a movie library");
    }
}
