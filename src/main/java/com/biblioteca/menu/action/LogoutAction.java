package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.io.IO;

public class LogoutAction implements Actionable {
    private static final String LOG_OUT = "Log Out";
    private final IO anIOStream;
    private final AccountManager accountManager;

    public LogoutAction(IO anIOStream, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.accountManager = accountManager;
    }

    @Override
    public void execute() {
        accountManager.logout();
        anIOStream.displayWithNewLine("Logging out...\nThank you :)");
    }

    @Override
    public String displayName() {
        return LOG_OUT;
    }
}
