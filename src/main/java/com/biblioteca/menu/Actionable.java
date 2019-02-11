package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;

interface Actionable {

    void execute() throws NotABookLibraryException, NotAMovieLibraryException;

    String displayName();
}
