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
    private final LibraryView libraryView;

    private DisplayAction(IO anIOStream, Library aLibrary, LibraryView libraryView) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.libraryView = libraryView;
    }

    static DisplayAction bookDisplayAction(IO io, Library library) {
        return new DisplayAction(io, library, new AvailableBookLibraryView(io));
    }

    static DisplayAction movieDisplayAction(IO io, Library library) {
        return new DisplayAction(io, library, new AvailableMovieLibraryView(io));
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(String.format(EMPTY_LIBRARY, contentType()));
            return;
        }
        List<LibraryItem> availableItems = aLibrary.availableItems();
        libraryView.display(availableItems);
    }

    private String  contentType() {
        if(libraryView instanceof AvailableBookLibraryView) {
           return "book";
        }
        if(libraryView instanceof AvailableMovieLibraryView) {
            return "movie";
        }
        return null;
    }

    @Override
    public String displayName() {
        return String.format(LIST_ALL, contentType());
    }

}
