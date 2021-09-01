package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.io.IO;

public class LoginAction implements Actionable {
    private static final String LOG_IN = "Log In";
    private static final String WELCOME = "Welcome";
    private static final String WRONG_CREDENTIAL = "Credentials are wrong\nPlease try again";
    private static final String LIBRARY_NUMBER = "Library Number: ";
    private static final String PASSWORD = "Password: ";

    private final IO anIOStream;
    private final AccountManager accountManager;

    public LoginAction(IO anIOStream, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.accountManager = accountManager;
    }

    @Override
    public void execute() {
        int noOfAttempts = 3;
        while (noOfAttempts-- > 0) {
            anIOStream.display(LIBRARY_NUMBER);
            String libraryNo = anIOStream.readInputAsString();
            anIOStream.display(PASSWORD);
            String password = anIOStream.readInputAsString();

            accountManager.login(libraryNo, password);
            if (accountManager.isLoggedIn()) {
                break;
            } else {
                anIOStream.displayWithNewLine(WRONG_CREDENTIAL);
            }
        }
        if (accountManager.isLoggedIn()) {
            anIOStream.displayWithNewLine(WELCOME);
        }
    }

    @Override
    public String displayName() {
        return LOG_IN;
    }
}
