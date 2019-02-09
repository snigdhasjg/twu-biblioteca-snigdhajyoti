package com.biblioteca.menu;

import com.biblioteca.InvalidBookNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

public class CheckOutAction implements Actionable {
    private static final String CHECKOUT = "Checkout";
    private static final String ENTER_BOOK_NAME = "Enter book name: ";
    private static final String EMPTY_BOOK = "Sorry! No book in Library";
    private static final String BOOK_NOT_AVAILABLE = "That book is not available";
    private static final String THANK_YOU_ENJOY_THE_BOOK = "Thank you! Enjoy the book";

    private final IO anIOStream;
    private final Library aLibrary;

    CheckOutAction(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
    }

    @Override
    public void execute() {
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
            anIOStream.displayWithNewLine(BOOK_NOT_AVAILABLE);
        }
    }

    @Override
    public String displayName() {
        return CHECKOUT;
    }
}
