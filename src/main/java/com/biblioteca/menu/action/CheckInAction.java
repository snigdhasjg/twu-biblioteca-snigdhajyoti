package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.exception.UserDoesNotMatchException;
import com.biblioteca.io.IO;

public class CheckInAction implements Actionable {
    private static final String RETURN = "Return ";
    private static final String ENTER_ITEM_NAME = "Enter %s name: ";
    private static final String UNSUCCESSFUL_RETURN = "That is not a valid %s to return";
    private static final String USER_MISMATCH = "This %s doesn't belongs to you";
    private static final String SUCCESSFUL_RETURN = "Thank you for returning the %s";

    private final IO anIOStream;
    private final Library aLibrary;
    private final String contentType;
    private final AccountManager accountManager;

    public CheckInAction(IO anIOStream, Library aLibrary, String contentType, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
        this.accountManager = accountManager;
    }

    @Override
    public void execute() {
        anIOStream.display(String.format(ENTER_ITEM_NAME, contentType));
        String bookName = anIOStream.readInputAsString();
        try {
            aLibrary.checkIn(bookName, accountManager.currentUser());
            anIOStream.displayWithNewLine(String.format(SUCCESSFUL_RETURN, contentType));
        } catch (InvalidItemNameException exception) {
            anIOStream.displayWithNewLine(String.format(UNSUCCESSFUL_RETURN, contentType));
        } catch (UserDoesNotMatchException exception) {
            anIOStream.displayWithNewLine(String.format(USER_MISMATCH, contentType));
        }
    }

    @Override
    public String displayName() {
        return RETURN + contentType;
    }
}
