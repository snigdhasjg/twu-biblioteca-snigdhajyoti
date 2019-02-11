package com.biblioteca.menu;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

class CheckInAction implements Actionable {
    private static final String RETURN = "Return";
    private static final String ENTER_ITEM_NAME = "Enter item name: ";
    private static final String UNSUCCESSFUL_RETURN = "That is not a valid item to return";
    private static final String SUCCESSFUL_RETURN = "Thank you for returning the item";

    private final IO anIOStream;
    private final Library aLibrary;

    CheckInAction(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
    }

    @Override
    public void execute() {
        anIOStream.display(ENTER_ITEM_NAME);
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            anIOStream.displayWithNewLine(SUCCESSFUL_RETURN);
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(UNSUCCESSFUL_RETURN);
        }
    }

    @Override
    public String displayName() {
        return RETURN;
    }
}
