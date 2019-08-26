package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;

import java.util.List;

class DisplayAction implements Actionable {
    private static final String LIST_ALL = "List all %ss";
    private static final String EMPTY_LIBRARY = "Sorry! No %s in Library";

    private final IO anIOStream;
    private final Library aLibrary;
    private final View view;

    private DisplayAction(IO anIOStream, Library aLibrary, View view) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.view = view;
    }

    static DisplayAction bookDisplayAction(IO io, Library library) {
        return new DisplayAction(io, library, new AvailableBookView(io));
    }

    static DisplayAction movieDisplayAction(IO io, Library library) {
        return new DisplayAction(io, library, new AvailableMovieView(io));
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(String.format(EMPTY_LIBRARY, contentType()));
            return;
        }
        List<LibraryItem> availableItems = aLibrary.availableItems();
        view.display(availableItems);
    }

    private String  contentType() {
        if(view instanceof AvailableBookView) {
           return "book";
        }
        if(view instanceof AvailableMovieView) {
            return "movie";
        }
        return null;
    }

    @Override
    public String displayName() {
        return String.format(LIST_ALL, contentType());
    }

}
