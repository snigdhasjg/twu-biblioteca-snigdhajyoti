package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.account.AccountType;
import com.biblioteca.io.IO;

import java.util.Map;

//Represents a list of option available in a library
public class Menu {
    private static final String TYPE_QUIT_TO_EXIT = "type \"quit\" to exit";
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String MENU_LINE = "\n\n.....................MENU.....................";
    private static final String DOTTED_LINE = new String(new char[46]).replace("\0", ".");
    private static final String ACTION_DISPLAY_LINE = "\t\t\t%s. %s";
    private static final String QUIT = "quit";

    private AccountType accountType;
    private final IO anIOStream;
    private final Library aBookLibrary;
    private final Library aMovieLibrary;
    private final AccountManager accountManager;

    public Menu(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.aBookLibrary = aBookLibrary;
        this.aMovieLibrary = aMovieLibrary;
        this.accountManager = accountManager;
    }

    public void options() {
        InvalidAction invalidAction = new InvalidAction(anIOStream);
        while (true) {
            Map<String, Actionable> options;
            if(accountManager.isLoggedIn()) {
                options = MenuOptionsFactory.getMenuOptionAfterLogin(anIOStream, aBookLibrary, aMovieLibrary, accountManager);
            }else{
                options = MenuOptionsFactory.getMenuOptionBeforeLogin(anIOStream, aBookLibrary, aMovieLibrary, accountManager);
            }
            displayMenu(options);
            String inputOption = anIOStream.readInputAsString().trim();
            if (inputOption.equalsIgnoreCase(QUIT)) {
                break;
            }
            options.getOrDefault(inputOption, invalidAction).execute();
        }
    }

    private void displayMenu(Map<String, Actionable> options) {
        anIOStream.displayWithNewLine(MENU_LINE);

        options.forEach((key, action) -> anIOStream.displayWithNewLine(
                String.format(ACTION_DISPLAY_LINE,key,action.displayName())));

        anIOStream.displayWithNewLine("\t\t\t" + TYPE_QUIT_TO_EXIT);
        anIOStream.displayWithNewLine(DOTTED_LINE);
        anIOStream.display(ENTER_YOUR_CHOICE);
    }

    AccountType getAccountType() {
        return accountType;
    }

    void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
