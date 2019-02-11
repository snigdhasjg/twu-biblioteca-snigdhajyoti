package com.biblioteca.menu;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

class CheckOutAction implements Actionable {
    private static final String CHECKOUT = "Checkout";
    private static final String ENTER_ITEM_NAME = "Enter item name: ";
    private static final String EMPTY_LIBRARY = "Sorry! No item in Library";
    private static final String ITEM_NOT_AVAILABLE = "That item is not available";
    private static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the item";

    private final IO anIOStream;
    private final Library aLibrary;

    CheckOutAction(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_LIBRARY);
            return;
        }
        anIOStream.display(ENTER_ITEM_NAME);
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkOut(bookName);
            anIOStream.displayWithNewLine(SUCCESSFUL_CHECKOUT);
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(ITEM_NOT_AVAILABLE);
        }
    }

    @Override
    public String displayName() {
        return CHECKOUT;
    }
}
