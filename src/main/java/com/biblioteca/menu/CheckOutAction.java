package com.biblioteca.menu;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

class CheckOutAction implements Actionable {
    private static final String CHECKOUT = "Checkout ";
    private static final String ENTER_ITEM_NAME = "Enter %s name: ";
    private static final String EMPTY_LIBRARY = "Sorry! No %s in Library";
    private static final String ITEM_NOT_AVAILABLE = "That %s is not available";
    private static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the %s";

    private final IO anIOStream;
    private final Library aLibrary;
    private final String contentType;

    CheckOutAction(IO anIOStream, Library aLibrary, String contentType) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(String.format(EMPTY_LIBRARY, contentType));
            return;
        }
        anIOStream.display(String.format(ENTER_ITEM_NAME, contentType));
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkOut(bookName);
            anIOStream.displayWithNewLine(String.format(SUCCESSFUL_CHECKOUT, contentType));
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(String.format(ITEM_NOT_AVAILABLE, contentType));
        }
    }

    @Override
    public String displayName() {
        return CHECKOUT + contentType;
    }
}
