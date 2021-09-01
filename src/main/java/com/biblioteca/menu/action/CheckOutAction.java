package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

public class CheckOutAction implements Actionable {
    private static final String CHECKOUT = "Checkout ";
    private static final String ENTER_ITEM_NAME = "Enter %s name: ";
    private static final String EMPTY_LIBRARY = "Sorry! No %s in Library";
    private static final String ITEM_NOT_AVAILABLE = "That %s is not available";
    private static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the %s";

    private final IO anIOStream;
    private final Library aLibrary;
    private final String contentType;
    private final AccountManager accountManager;

    public CheckOutAction(IO anIOStream, Library aLibrary, String contentType, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
        this.accountManager = accountManager;
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
            aLibrary.checkOut(bookName, accountManager.currentUser());
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
