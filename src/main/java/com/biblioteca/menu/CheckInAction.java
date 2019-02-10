package com.biblioteca.menu;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

class CheckInAction implements Actionable {
    private static final String RETURN = "Return";
    private static final String ENTER_BOOK_NAME = "Enter book name: ";
    private static final String UNSUCCESSFUL_RETURN = "That is not a valid book to return";
    private static final String THANK_YOU_FOR_RETURNING_THE_BOOK = "Thank you for returning the book";

    private final IO anIOStream;
    private final Library aLibrary;

    CheckInAction(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
    }

    @Override
    public void execute() {
        anIOStream.display(ENTER_BOOK_NAME);
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            anIOStream.displayWithNewLine(THANK_YOU_FOR_RETURNING_THE_BOOK);
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(UNSUCCESSFUL_RETURN);
        }
    }

    @Override
    public String displayName() {
        return RETURN;
    }
}
