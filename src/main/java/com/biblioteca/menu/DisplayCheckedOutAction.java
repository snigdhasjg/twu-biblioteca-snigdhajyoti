package com.biblioteca.menu;

import com.biblioteca.Library;
import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;

import java.util.Map;

public class DisplayCheckedOutAction implements Actionable {
    private static final String LIST_ALL = "Checked out %ss";

    private final IO anIOStream;
    private final Library aLibrary;
    private final CheckedOutView checkedOutView;

    private DisplayCheckedOutAction(IO anIOStream, Library aLibrary, CheckedOutView checkedOutView) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.checkedOutView = checkedOutView;
    }

    static DisplayCheckedOutAction movie(IO anIOStream, Library aLibrary){
        return new DisplayCheckedOutAction(anIOStream,aLibrary, new CheckedOutMovieLibraryView(anIOStream));
    }

    static DisplayCheckedOutAction book(IO anIOStream, Library aLibrary){
        return new DisplayCheckedOutAction(anIOStream,aLibrary, new CheckedOutBookLibraryView(anIOStream));
    }

    @Override
    public void execute() {
        if (aLibrary.isFull()) {
            anIOStream.displayWithNewLine(String.format("Sorry! All the %ss is in the library", contentType()));
            return;
        }
        Map<LibraryItem, IAccount> checkedOutItem = aLibrary.checkedOutItems();
        checkedOutView.display(checkedOutItem);
    }

    @Override
    public String displayName() {
        return String.format(LIST_ALL, contentType());
    }

    private String contentType() {
        if (checkedOutView instanceof CheckedOutBookLibraryView) {
            return "book";
        }
        if (checkedOutView instanceof CheckedOutMovieLibraryView) {
            return "movie";
        }
        return null;
    }
}
