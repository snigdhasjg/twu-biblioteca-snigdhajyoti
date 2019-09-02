package com.biblioteca.menu;

import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItem;

import java.util.List;

class AvailableBookLibraryView implements LibraryView {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String PUBLICATION_YEAR = "Year";

    private final IO io;

    AvailableBookLibraryView(IO io) {
        this.io = io;
    }

    public String header() {
        return String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
    }

    public String details(LibraryItem item) {
        Book aBook = (Book) item;
        return String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
    }

    public Integer length() {
        return 46;
    }

    public void display(List<LibraryItem> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        for (LibraryItem item : items) {
            String details = details(item);
            io.displayWithNewLine(details);
        }
    }
}
