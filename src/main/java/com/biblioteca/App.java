package com.biblioteca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents the main application
class App {
    final static String BOOK_DETAILS_FORMAT = "%-20s %-20s %-20s %-20s";
    private final IO aIOStream;
    private final Library aLibrary;
    private Map<String, Actionable> options;

    App(IO aIOStream, Library aLibrary) {
        this.aIOStream = aIOStream;
        this.aLibrary = aLibrary;
        this.options = new HashMap<>();
        setupMenu();
    }

    private void setupMenu() {
        options.put("1", this::displayAllBookList);
        options.put("2", this::checkOut);
        options.put("3", this::checkIn);
    }

    void start() {
        welcome();
        options();
    }

    private void welcome() {
        String WELCOME_MESSAGE = "Welcome to Biblioteca";
        aIOStream.displayWithNewLine(WELCOME_MESSAGE);
    }

    private void options() {
        while (true) {
            menu();
            String inputOption = aIOStream.readInputAsString();
            if (inputOption.equalsIgnoreCase("quit")) {
                break;
            }
            options.getOrDefault(inputOption, () -> aIOStream.displayWithNewLine("Select a valid option!")).execute();
        }
    }

    private void menu() {
        aIOStream.displayWithNewLine("\n...............................MENU................................");
        aIOStream.displayWithNewLine("1. List All Books");
        aIOStream.displayWithNewLine("2. Checkout");
        aIOStream.displayWithNewLine("3. Return");
        aIOStream.displayWithNewLine("\ntype \"quit\" to exit");
        aIOStream.displayWithNewLine("...................................................................");
        aIOStream.display("Enter your choice: ");
    }

    private void displayAllBookList() {
        if (aLibrary.isEmpty()) {
            aIOStream.displayWithNewLine("Sorry! No book in Library");
            return;
        }
        aIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, "SlNo", "Book Name", "Author", "Year"));
        aIOStream.displayWithNewLine("-------------------------------------------------------------------");
        int count = 1;
        List<Book> allBooks = aLibrary.listOfAllBooks();
        if (allBooks.size() == 0) {
            aIOStream.displayWithNewLine("No books in the Library right now");
        }
        for (Book eachBook : allBooks) {
            String bookDetails = String.format(BOOK_DETAILS_FORMAT, "" + count++ + ".", eachBook.title(), eachBook.author(), eachBook.year());
            aIOStream.displayWithNewLine(bookDetails);
        }
    }

    private void checkOut() {
        if (aLibrary.isEmpty()) {
            aIOStream.displayWithNewLine("Sorry! No book in Library");
            return;
        }
        aIOStream.display("Enter book name: ");
        String bookName = aIOStream.readInputAsString();
        try {
            aLibrary.checkOut(bookName);
            aIOStream.displayWithNewLine("Thank you! Enjoy the book");
        } catch (InvalidBookNameException exception) {
            aIOStream.displayWithNewLine("That book is not available");
        }
    }

    private void checkIn() {
        aIOStream.display("Enter book name you have: ");
        String bookName = aIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            aIOStream.displayWithNewLine("Thank you for returning the book");
        } catch (InvalidBookNameException exception) {
            aIOStream.displayWithNewLine("That is not a valid book to return");
        }
    }
}
