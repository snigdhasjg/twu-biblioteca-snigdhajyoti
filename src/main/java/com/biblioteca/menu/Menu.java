package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.io.IO;

import java.util.HashMap;
import java.util.Map;

//Represents a list of option available in a library
public class Menu {
    private static final String SELECT_A_VALID_OPTION = "Select a valid option!";
    private static final String TYPE_QUIT_TO_EXIT = "type \"quit\" to exit";
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String MENU_LINE = "\n.....................MENU.....................";
    private static final String DOTTED_LINE = new String(new char[46]).replace("\0", ".");

    private final IO anIOStream;
    private final Library aLibrary;
    private Map<String, Actionable> options;

    public Menu(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        options = new HashMap<>();
        setupMenu();
    }

    public void options() {
        while (true) {
            displayMenu();
            String inputOption = anIOStream.readInputAsString().trim();
            if (inputOption.equalsIgnoreCase("quit")) {
                break;
            }
            options.getOrDefault(inputOption, new Actionable() {
                @Override
                public void execute() {
                    anIOStream.displayWithNewLine(SELECT_A_VALID_OPTION);
                }

                @Override
                public String displayName() {
                    return null;
                }
            }).execute();
        }
    }

    private void setupMenu() {
        options.put("1", new BookDisplayAction(anIOStream, aLibrary));
        options.put("2", new CheckOutAction(anIOStream, aLibrary));
        options.put("3", new CheckInAction(anIOStream, aLibrary));
    }

    private void displayMenu() {
        anIOStream.displayWithNewLine(MENU_LINE);

        options.forEach((key, action) -> anIOStream.displayWithNewLine(key + ". " + action.displayName()));

        anIOStream.displayWithNewLine(TYPE_QUIT_TO_EXIT);
        anIOStream.displayWithNewLine(DOTTED_LINE);
        anIOStream.display(ENTER_YOUR_CHOICE);
    }
}
