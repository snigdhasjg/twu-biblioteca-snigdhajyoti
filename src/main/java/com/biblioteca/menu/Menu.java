package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.io.IO;

import java.util.HashMap;
import java.util.Map;

//Represents a list of option available in a library
public class Menu {
    private static final String TYPE_QUIT_TO_EXIT = "type \"quit\" to exit";
    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    private static final String MENU_LINE = "\n\n.....................MENU.....................";
    private static final String DOTTED_LINE = new String(new char[46]).replace("\0", ".");

    private final IO anIOStream;
    private final Library aBookLibrary;
    private final Library aMovieLibrary;
    private Map<String, Actionable> options;

    public Menu(IO anIOStream, Library aBookLibrary, Library aMovieLibrary) {
        this.anIOStream = anIOStream;
        this.aBookLibrary = aBookLibrary;
        this.aMovieLibrary = aMovieLibrary;
        options = new HashMap<>();
        setupMenu();
    }

    public void options(){
        while (true) {
            displayMenu();
            String inputOption = anIOStream.readInputAsString().trim();
            if (inputOption.equalsIgnoreCase("quit")) {
                break;
            }
            options.getOrDefault(inputOption, new InvalidAction(anIOStream)).execute();
        }
    }

    private void setupMenu() {
        options.put("1", new DisplayAction(anIOStream, aBookLibrary, "book"));
        options.put("2", new CheckOutAction(anIOStream, aBookLibrary, "book"));
        options.put("3", new CheckInAction(anIOStream, aBookLibrary, "book"));

        options.put("4", new DisplayAction(anIOStream, aMovieLibrary, "movie"));
        options.put("5", new CheckOutAction(anIOStream, aMovieLibrary, "movie"));
        options.put("6", new CheckInAction(anIOStream, aMovieLibrary, "movie"));
    }

    private void displayMenu() {
        anIOStream.displayWithNewLine(MENU_LINE);

        options.forEach((key, action) -> anIOStream.displayWithNewLine("\t\t\t" + key + ". " + action.displayName()));

        anIOStream.displayWithNewLine("\t\t\t" + TYPE_QUIT_TO_EXIT);
        anIOStream.displayWithNewLine(DOTTED_LINE);
        anIOStream.display(ENTER_YOUR_CHOICE);
    }
}
