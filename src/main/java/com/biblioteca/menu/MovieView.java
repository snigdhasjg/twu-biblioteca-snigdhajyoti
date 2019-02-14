package com.biblioteca.menu;

import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;

import java.util.List;

class MovieView implements View {
    private static final String MOVIE_DETAILS_FORMAT = "%-20s %-20s %-20s %-7s";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String DIRECTOR = "Director";
    private static final String YEAR = "Year";
    private static final String RATING = "Rating";

    private final IO io;

    MovieView(IO io) {
        this.io = io;
    }

    public String header() {
        return String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING);
    }

    public String details(LibraryItem item) {
        Movie aMovie = (Movie) item;
        return String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
    }

    public Integer length() {
        return 70;
    }

    public void display(List<LibraryItem> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        for (LibraryItem item : items) {
            String details = details(item);
            io.displayWithNewLine(details);
        }
    }
}
