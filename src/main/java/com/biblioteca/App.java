package com.biblioteca;

import java.util.Arrays;

import static com.biblioteca.Book.book;

// Represents the main application
class App {

    private final IO aIOStream;
    private final Library aLibrary;

    App(IO aIOStream) {
        this.aIOStream = aIOStream;
        this.aLibrary = initializeTheLibrary();
    }

    void start(){
        this.welcome();
        this.displayAllBookList();
    }

    private void welcome() {
        String WELCOME_MESSAGE = "Welcome to Biblioteca";
        aIOStream.display(WELCOME_MESSAGE);
    }

    private void displayAllBookList() {
        aIOStream.display("\nHere are all the books available right now in the library:");
        aIOStream.display(aLibrary.returnAllBooksName());
    }

    private Library initializeTheLibrary(){
        final String book1_name = "Head First JAVA";
        Book book1 = book(book1_name);
        final String book2_name = "Extreme Programming";
        Book book2 = book(book2_name);
        return new Library(Arrays.asList(book1,book2));
    }
}
