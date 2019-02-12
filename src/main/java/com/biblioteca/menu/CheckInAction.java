package com.biblioteca.menu;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.Library;
import com.biblioteca.io.IO;

class CheckInAction implements Actionable {
    private static final String RETURN = "Return ";
    private static final String ENTER_ITEM_NAME = "Enter %s name: ";
    private static final String UNSUCCESSFUL_RETURN = "That is not a valid %s to return";
    private static final String SUCCESSFUL_RETURN = "Thank you for returning the %s";

    private final IO anIOStream;
    private final Library aLibrary;
    private final String contentType;

    CheckInAction(IO anIOStream, Library aLibrary, String contentType) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
    }

    @Override
    public void execute() {
        anIOStream.display(String.format(ENTER_ITEM_NAME, contentType));
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName);
            anIOStream.displayWithNewLine(String.format(SUCCESSFUL_RETURN, contentType));
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(String.format(UNSUCCESSFUL_RETURN, contentType));
        }
    }

    @Override
    public String displayName() {
        return RETURN + contentType;
    }
}
