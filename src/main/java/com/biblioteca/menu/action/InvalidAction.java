package com.biblioteca.menu.action;

import com.biblioteca.io.IO;

public class InvalidAction implements Actionable {
    private static final String SELECT_A_VALID_OPTION = "Select a valid option!";
    private final IO anIOStream;

    public InvalidAction(IO anIOStream) {
        this.anIOStream = anIOStream;
    }

    @Override
    public void execute() {
        anIOStream.displayWithNewLine(SELECT_A_VALID_OPTION);
    }

    @Override
    public String displayName() {
        return "";
    }
}
