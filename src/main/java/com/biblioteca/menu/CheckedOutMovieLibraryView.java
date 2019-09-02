package com.biblioteca.menu;

import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;

import java.util.Map;

public class CheckedOutMovieLibraryView implements CheckedOutView {
    private static final String CHECKED_OUT_MOVIE_HEADER = "%-20s %-20s %-8s";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String USER_NAME = "User Name";
    private static final String LIBRARY_NUMBER = "Library Number";

    private final IO io;

    CheckedOutMovieLibraryView(IO io){
        this.io = io;
    }

    @Override
    public String header() {
        return String.format(CHECKED_OUT_MOVIE_HEADER, MOVIE_NAME,USER_NAME,LIBRARY_NUMBER);
    }

    @Override
    public String details(LibraryItem item, IAccount anAccount) {
        Movie aMovie = (Movie) item;
        return String.format(CHECKED_OUT_MOVIE_HEADER,aMovie.title(),anAccount.getName(),anAccount.getLibraryNumber());
    }

    @Override
    public Integer length() {
        return 46;
    }

    @Override
    public void display(Map<LibraryItem, IAccount> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        for (Map.Entry<LibraryItem, IAccount> entry : items.entrySet()) {
            String details = details(entry.getKey(), entry.getValue());
            io.displayWithNewLine(details);
        }
    }
}
