package com.biblioteca.menu.view;

import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;

import java.util.List;

public class AvailableMovieLibraryView extends LibraryView {
    private static final String MOVIE_DETAILS_FORMAT = "%-20s %-20s %-20s %-7s";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String DIRECTOR = "Director";
    private static final String YEAR = "Year";
    private static final String RATING = "Rating";
    private static final String MOVIE = "movie";

    public AvailableMovieLibraryView(IO io) {
        super(io);
    }

    @Override
    public String contentType() {
        return MOVIE;
    }

    @Override
    protected String header() {
        return String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING);
    }

    @Override
    protected String details(LibraryItem item) {
        Movie aMovie = (Movie) item;
        return String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
    }

    @Override
    protected Integer length() {
        return 70;
    }
}
