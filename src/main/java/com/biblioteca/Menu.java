package com.biblioteca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents a list of option available in a library
class Menu {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String SELECT_A_VALID_OPTION = "Select a valid option!";
    private static final String EMPTY_BOOK = "Sorry! No book in Library";
    private static final String LIST_ALL_BOOKS = "1. List All Books";
    private static final String CHECKOUT = "2. Checkout";
    private static final String RETURN = "3. Return";
    private static final String TYPE_QUIT_TO_EXIT = "type \"quit\" to exit";
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String ENTER_BOOK_NAME = "Enter book name: ";
    private static final String THANK_YOU_ENJOY_THE_BOOK = "Thank you! Enjoy the book";
    private static final String THAT_BOOK_IS_NOT_AVAILABLE = "That book is not available";
    private static final String THANK_YOU_FOR_RETURNING_THE_BOOK = "Thank you for returning the book";
    private static final String THAT_IS_NOT_A_VALID_BOOK_TO_RETURN = "That is not a valid book to return";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String YEAR = "Year";
    private static final String MENU_LINE = "\n.....................MENU.....................";
    private static final String DOTTED_LINE = new String(new char[46]).replace("\0", ".");

    private final IO anIOStream;
    private final Library aLibrary;
    private Map<String, Actionable> options;

    Menu(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        options = new HashMap<>();
        setupMenu();
    }

    void options() {
        while (true) {
            displayMenu();
            String inputOption = anIOStream.readInputAsString();
            if (inputOption.equalsIgnoreCase("quit")) {
                break;
            }
            options.getOrDefault(inputOption, () -> anIOStream.displayWithNewLine(SELECT_A_VALID_OPTION)).execute();
        }
    }

    private void setupMenu() {
        options.put("1", this::displayAllBookList);
        options.put("2", this::checkOut);
        options.put("3", this::checkIn);
    }

    private void displayMenu() {
        anIOStream.displayWithNewLine(MENU_LINE);
        anIOStream.displayWithNewLine(LIST_ALL_BOOKS);
        anIOStream.displayWithNewLine(CHECKOUT);
        anIOStream.displayWithNewLine(RETURN);
        anIOStream.displayWithNewLine(TYPE_QUIT_TO_EXIT);
        anIOStream.displayWithNewLine(DOTTED_LINE);
        anIOStream.display(ENTER_YOUR_CHOICE);
    }

    private void displayAllBookList() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_BOOK);
            return;
        }
        anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, YEAR));
        anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
        List<Book> allBooks = aLibrary.listOfAvailableBooks();
        for (Book eachBook : allBooks) {
            String bookDetails = String.format(BOOK_DETAILS_FORMAT, eachBook.title(), eachBook.author(), eachBook.year());
            anIOStream.displayWithNewLine(bookDetails);
        }
    }

    private void checkOut() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_BOOK);
            return;
        }
        anIOStream.display(ENTER_BOOK_NAME);
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkOut(bookName);
            anIOStream.displayWithNewLine(THANK_YOU_ENJOY_THE_BOOK);
        } catch (InvalidBookNameException exception) {
            anIOStream.displayWithNewLine(THAT_BOOK_IS_NOT_AVAILABLE);
        }
    }

    private void checkIn() {
        anIOStream.display(ENTER_BOOK_NAME);
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            anIOStream.displayWithNewLine(THANK_YOU_FOR_RETURNING_THE_BOOK);
        } catch (InvalidBookNameException exception) {
            anIOStream.displayWithNewLine(THAT_IS_NOT_A_VALID_BOOK_TO_RETURN);
        }
    }
}
