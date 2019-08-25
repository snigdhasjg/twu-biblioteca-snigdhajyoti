package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;

import java.util.Map;

public class LoginMenu {
    private final IO anIOStream;
    private final Library aBookLibrary;
    private final Library aMovieLibrary;
    private final AccountManager accountManager;
    private Map<String, Actionable> options;

    public LoginMenu(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.aBookLibrary = aBookLibrary;
        this.aMovieLibrary = aMovieLibrary;
        this.accountManager = accountManager;
    }

    public void login() {
        while(true) {
            anIOStream.display("Library Number: ");
            String libraryNo = anIOStream.readInputAsString();
            anIOStream.display("Password: ");
            String password = anIOStream.readInputAsString();

            IAccount currentSession = accountManager.login(libraryNo, password);
            if (currentSession != null) {
                aBookLibrary.setSession(currentSession);
                aMovieLibrary.setSession(currentSession);
                return;
            }else{
                anIOStream.displayWithNewLine("Credentials are wrong\nPlease try again");
            }
        }
    }
}
