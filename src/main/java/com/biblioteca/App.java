package com.biblioteca;

import java.util.List;

// Represents the main application
class App {

    private static final String BOOK_DETAILS_FORMAT = "%-4s %-19s %-25s %s";
    private final IO aIOStream;
    private final Library aLibrary;

    App(IO aIOStream, Library aLibrary) {
        this.aIOStream = aIOStream;
        this.aLibrary = aLibrary;
    }

    void start() {
        welcome();
        options();
    }

    private void options() {
        while (true) {
            menu();
            switch (aIOStream.readInputAsString()) {
                case "1":
                    displayAllBookList();
                    break;
                case "quit":
                    return; //System.exit(0);
                default:
                    aIOStream.displayWithNewLine("Select a valid option!");
                    break;
            }
        }
    }

    private void menu() {
        aIOStream.displayWithNewLine(".......................................................");
        aIOStream.displayWithNewLine("1. List All Books");
        aIOStream.display("Enter your choice: ");
    }

    private void welcome() {
        String WELCOME_MESSAGE = "Welcome to Biblioteca";
        aIOStream.displayWithNewLine(WELCOME_MESSAGE);
    }

    private void displayAllBookList() {
        aIOStream.displayWithNewLine(String.format("%-4s %-19s %-25s %s", "SlNo", "Book Name", "Author", "Year"));
        aIOStream.displayWithNewLine("-------------------------------------------------------");
        int count = 1;
        List<Book> allBooks = aLibrary.listOfAllBooks();
        if (allBooks.size() == 0) {
            aIOStream.displayWithNewLine("No books in the Library right now");
        }
        for (Book eachBook : allBooks) {
            String bookDetails = String.format(BOOK_DETAILS_FORMAT, "" + count++, eachBook.title(), eachBook.author(), eachBook.year());
            aIOStream.displayWithNewLine(bookDetails);
        }
    }
}
