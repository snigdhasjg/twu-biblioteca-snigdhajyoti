package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.io.IO;

class LogoutAction implements Actionable{
    private static final String LOG_OUT = "Log Out";
    private final IO anIOStream;
    private final AccountManager accountManager;

    LogoutAction(IO anIOStream, AccountManager accountManager){
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
