package com.biblioteca;

import com.biblioteca.io.IO;
import com.biblioteca.menu.LoginMenu;
import com.biblioteca.menu.Menu;

// Represents the starting of biblioteca application
class App {
    private static final String WELCOME_TO_BIBLIOTECA = "Welcome to Biblioteca";
    private final IO anIOStream;
    private final LoginMenu aLoginMenu;
    private final Menu aMenu;


    App(IO anIOStream, LoginMenu aLoginMenu, Menu aMenu) {
        this.anIOStream = anIOStream;
        this.aLoginMenu = aLoginMenu;
        this.aMenu = aMenu;
    }

    void start(){
        welcome();
        while(true) {
            aLoginMenu.login();
            aMenu.options();
        }
    }

    private void welcome() {
        anIOStream.displayWithNewLine(WELCOME_TO_BIBLIOTECA);
    }
}
