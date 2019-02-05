package com.biblioteca;

// Represents the main application
class App {

    private final IO aIOStream;
    private final String WELCOME_MESSAGE = "Welcome to Main";

    App(IO aIOStream) {
        this.aIOStream = aIOStream;
    }

    void welcome() {
        aIOStream.display(WELCOME_MESSAGE);
    }
}
