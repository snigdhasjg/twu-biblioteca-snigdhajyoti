package com.biblioteca.menu.view;

import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;

import java.util.Map;

public class CheckedOutMovieLibraryView extends CheckedOutView {
    private static final String CHECKED_OUT_MOVIE_HEADER = "%-20s %-20s %-8s";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String USER_NAME = "User Name";
    private static final String LIBRARY_NUMBER = "Library Number";

    public CheckedOutMovieLibraryView(IO io) {
        super(io);
    }

    @Override
    protected String header() {
        return String.format(CHECKED_OUT_MOVIE_HEADER, MOVIE_NAME, USER_NAME, LIBRARY_NUMBER);
    }

    @Override
    protected String details(LibraryItem item, IAccount anAccount) {
        Movie aMovie = (Movie) item;
        return String.format(CHECKED_OUT_MOVIE_HEADER, aMovie.title(), anAccount.getName(), anAccount.getLibraryNumber());
    }

    @Override
    protected Integer length() {
        return 57;
    }
}
