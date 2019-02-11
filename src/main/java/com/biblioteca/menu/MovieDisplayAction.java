package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItems;
import com.biblioteca.items.Movie;

import java.util.List;

public class MovieDisplayAction implements Actionable{
    private static final String MOVIE_DETAILS_FORMAT = "%-20s %-20s %-20s %-7s";
    private static final String LIST_ALL_MOVIES = "List All Movies";
    private static final String EMPTY_MOVIE_LIBRARY = "Sorry! No movie in Library";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String DIRECTOR = "Director";
    private static final String YEAR = "Year";
    private static final String RATING = "Rating";

    private final IO anIOStream;
    private final Library movieLibrary;

    MovieDisplayAction(IO anIOStream, Library movieLibrary) {
        this.anIOStream = anIOStream;
        this.movieLibrary = movieLibrary;
    }

    @Override
    public void execute() throws NotAMovieLibraryException {
        if (movieLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_MOVIE_LIBRARY);
            return;
        }
        List<LibraryItems> allItems = movieLibrary.listOfAvailableItems();
        if(allItems.get(0) instanceof Movie) {
            anIOStream.displayWithNewLine(String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING));
            anIOStream.displayWithNewLine(new String(new char[70]).replace("\0", "-"));
            for (LibraryItems eachItem : allItems) {
                Movie aMovie = (Movie) eachItem;
                String movieDetails = String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(),aMovie.rating());
                anIOStream.displayWithNewLine(movieDetails);
            }
            return;
        }
        throw new NotAMovieLibraryException();
    }

    @Override
    public String displayName() {
        return LIST_ALL_MOVIES;
    }
}
