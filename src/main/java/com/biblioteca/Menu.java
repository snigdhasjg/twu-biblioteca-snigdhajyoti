package com.biblioteca;

//Represents a list of option available in a library
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Menu {
    private final static String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";

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
            menu();
            String inputOption = anIOStream.readInputAsString();
            if (inputOption.equalsIgnoreCase("quit")) {
                break;
            }
            options.getOrDefault(inputOption, () -> anIOStream.displayWithNewLine("Select a valid option!")).execute();
        }
    }

    private void setupMenu() {
        options.put("1", this::displayAllBookList);
        options.put("2", this::checkOut);
        options.put("3", this::checkIn);
    }

    private void menu() {
        anIOStream.displayWithNewLine("\n.....................MENU.....................");
        anIOStream.displayWithNewLine("1. List All Books");
        anIOStream.displayWithNewLine("2. Checkout");
        anIOStream.displayWithNewLine("3. Return");
        anIOStream.displayWithNewLine("type \"quit\" to exit");
        anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "."));
        anIOStream.display("Enter your choice: ");
    }

    private void displayAllBookList() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine("Sorry! No book in Library");
            return;
        }
        anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, "Book Name", "Author", "Year"));
        anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
        List<Book> allBooks = aLibrary.listOfAllBooks();
        if (allBooks.size() == 0) {
            anIOStream.displayWithNewLine("No books in the Library right now");
        }
        for (Book eachBook : allBooks) {
            String bookDetails = String.format(BOOK_DETAILS_FORMAT, eachBook.title(), eachBook.author(), eachBook.year());
            anIOStream.displayWithNewLine(bookDetails);
        }
    }

    private void checkOut() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine("Sorry! No book in Library");
            return;
        }
        anIOStream.display("Enter book name: ");
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkOut(bookName);
            anIOStream.displayWithNewLine("Thank you! Enjoy the book");
        } catch (InvalidBookNameException exception) {
            anIOStream.displayWithNewLine("That book is not available");
        }
    }

    private void checkIn() {
        anIOStream.display("Enter book name you have: ");
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            anIOStream.displayWithNewLine("Thank you for returning the book");
        } catch (InvalidBookNameException exception) {
            anIOStream.displayWithNewLine("That is not a valid book to return");
        }
    }
}
