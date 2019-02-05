package com.biblioteca;

// Represents the main application
class App {

    private final IO aIOStream;

    App(IO aIOStream) {
        this.aIOStream = aIOStream;
    }

    void welcome() {
        aIOStream.display("Welcome to Main");
    }
}
