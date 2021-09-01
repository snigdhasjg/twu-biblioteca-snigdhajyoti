package com.biblioteca.menu.view;

import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;

import java.util.List;

public class AvailableMovieLibraryView implements LibraryView {
    private static final String MOVIE_DETAILS_FORMAT = "%-20s %-20s %-20s %-7s";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String DIRECTOR = "Director";
    private static final String YEAR = "Year";
    private static final String RATING = "Rating";
    private static final String MOVIE = "movie";

    private final IO io;

    public AvailableMovieLibraryView(IO io) {
        this.io = io;
    }

    @Override
    public String header() {
        return String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING);
    }

    @Override
    public String details(LibraryItem item) {
        Movie aMovie = (Movie) item;
        return String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
    }

    @Override
    public Integer length() {
        return 70;
    }

    @Override
    public String contentType() {
        return MOVIE;
    }

    @Override
    public void display(List<LibraryItem> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        for (LibraryItem item : items) {
            String details = details(item);
            io.displayWithNewLine(details);
        }
    }
}
