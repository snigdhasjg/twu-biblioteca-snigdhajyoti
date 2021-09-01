package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.account.AccountType;
import com.biblioteca.io.IO;
import com.biblioteca.menu.action.Actionable;
import com.biblioteca.menu.action.InvalidAction;

import java.util.Map;

//Represents a list of option available in a library
public class Menu {
    private static final String TYPE_QUIT_TO_EXIT = "type \"quit\" to exit";
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String MENU_LINE = "\n\n.....................MENU.....................";
    private static final String DOTTED_LINE = new String(new char[46]).replace("\0", ".");
    private static final String ACTION_DISPLAY_LINE = "\t\t\t%s. %s";
    private static final String QUIT = "quit";

    private final IO anIOStream;
    private final AccountManager accountManager;

    private final Map<String, Actionable> customerOptionAfterLogin;
    private final Map<String, Actionable> optionBeforeLogin;
    private final Map<String, Actionable> adminOptionAfterLogin;

    public Menu(IO anIOStream, Library aBookLibrary, Library aMovieLibrary, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.accountManager = accountManager;
        this.customerOptionAfterLogin = MenuOptionsFactory.getMenuOptionForCustomerAfterLogin(anIOStream, aBookLibrary, aMovieLibrary, accountManager);
        this.optionBeforeLogin = MenuOptionsFactory.getMenuOptionBeforeLogin(anIOStream, aBookLibrary, aMovieLibrary, accountManager);
        this.adminOptionAfterLogin = MenuOptionsFactory.getMenuOptionForAdminAfterLogin(anIOStream, aBookLibrary, aMovieLibrary, accountManager);
    }

    public void options() {
        InvalidAction invalidAction = new InvalidAction(anIOStream);
        while (true) {
            Map<String, Actionable> options;
            if (accountManager.isLoggedIn()) {
                if (accountManager.currentUser().getAccountType() == AccountType.customer) {
                    options = customerOptionAfterLogin;
                } else {
                    options = adminOptionAfterLogin;
                }
            } else {
                options = optionBeforeLogin;
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
                String.format(ACTION_DISPLAY_LINE, key, action.displayName())));

        anIOStream.displayWithNewLine("\t\t\t" + TYPE_QUIT_TO_EXIT);
        anIOStream.displayWithNewLine(DOTTED_LINE);
        anIOStream.display(ENTER_YOUR_CHOICE);
    }
}
