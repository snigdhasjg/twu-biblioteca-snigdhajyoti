package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

import java.util.HashMap;
import java.util.Map;

class MenuOptionsFactory {

    private static final String BOOK = "book";
    private static final String MOVIE = "movie";

    static Map<String, Actionable> getMenuOptionForCustomerAfterLogin(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        Map<String, Actionable> _options = new HashMap<>();

        _options.put("1", DisplayAction.bookDisplayAction(anIOStream, aBookLibrary));
        _options.put("2", new CheckOutAction(anIOStream, aBookLibrary, BOOK, accountManager));
        _options.put("3", new CheckInAction(anIOStream, aBookLibrary, BOOK, accountManager));

        _options.put("4", DisplayAction.movieDisplayAction(anIOStream, aMovieLibrary));
        _options.put("5", new CheckOutAction(anIOStream, aMovieLibrary, MOVIE, accountManager));
        _options.put("6", new CheckInAction(anIOStream, aMovieLibrary, MOVIE, accountManager));

        _options.put("7", new ProfileOption(anIOStream, accountManager));
        _options.put("8", new LogoutAction(anIOStream, accountManager));

        return _options;
    }

    static Map<String, Actionable> getMenuOptionBeforeLogin(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        Map<String, Actionable> _options = new HashMap<>();

        _options.put("1", DisplayAction.bookDisplayAction(anIOStream, aBookLibrary));

        _options.put("2", DisplayAction.movieDisplayAction(anIOStream, aMovieLibrary));

        _options.put("3", new LoginAction(anIOStream, accountManager));

        return _options;
    }

    static Map<String, Actionable> getMenuOptionForAdminAfterLogin(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        Map<String, Actionable> _options = new HashMap<>();

        _options.put("1", DisplayAction.bookDisplayAction(anIOStream, aBookLibrary));

        _options.put("3", DisplayAction.movieDisplayAction(anIOStream, aMovieLibrary));

        _options.put("5", new ProfileOption(anIOStream, accountManager));
        _options.put("6", new LogoutAction(anIOStream, accountManager));

        return _options;
    }
}
