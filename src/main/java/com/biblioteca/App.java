package com.biblioteca;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.io.IO;
import com.biblioteca.menu.Menu;

// Represents the starting of biblioteca application
class App {
    private static final String WELCOME_TO_BIBLIOTECA = "Welcome to Biblioteca";
    private final IO anIOStream;
    private final Menu aMenu;


    App(IO anIOStream, Menu aMenu) {
        this.anIOStream = anIOStream;
        this.aMenu = aMenu;
    }

    void start(){
        welcome();
        aMenu.options();
    }

    private void welcome() {
        anIOStream.displayWithNewLine(WELCOME_TO_BIBLIOTECA);
    }
}
