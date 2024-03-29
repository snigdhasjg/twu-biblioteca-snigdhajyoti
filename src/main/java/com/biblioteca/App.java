package com.biblioteca;

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

    private void welcome() {
        anIOStream.displayWithNewLine(WELCOME_TO_BIBLIOTECA);
    }

    void start() {
        welcome();
        aMenu.options();
    }
}
