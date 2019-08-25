package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.io.IO;

import java.util.HashMap;
import java.util.Map;

class MenuOptionsFactory {

    private static final String BOOK = "book";
    private static final String MOVIE = "movie";

    static Map<String, Actionable> getMenuOptionAfterLogin(IO anIOStream, Library aBookLibrary, Library aMovieLibrary) {
        Map<String, Actionable> _options = new HashMap<>();

        _options.put("1", DisplayAction.bookDisplayAction(anIOStream, aBookLibrary));
        _options.put("2", new CheckOutAction(anIOStream, aBookLibrary, BOOK));
        _options.put("3", new CheckInAction(anIOStream, aBookLibrary, BOOK));

        _options.put("4", DisplayAction.movieDisplayAction(anIOStream, aMovieLibrary));
        _options.put("5", new CheckOutAction(anIOStream, aMovieLibrary, MOVIE));
        _options.put("6", new CheckInAction(anIOStream, aMovieLibrary, MOVIE));

        return _options;
    }

}
