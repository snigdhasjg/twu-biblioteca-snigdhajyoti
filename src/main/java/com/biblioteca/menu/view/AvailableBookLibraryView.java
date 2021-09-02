package com.biblioteca.menu.view;

import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItem;

import java.util.List;

public class AvailableBookLibraryView extends LibraryView {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String PUBLICATION_YEAR = "Year";
    private static final String BOOK = "book";

    public AvailableBookLibraryView(IO io) {
        super(io);
    }

    @Override
    public String contentType() {
        return BOOK;
    }

    @Override
    protected String header() {
        return String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
    }

    @Override
    protected String details(LibraryItem item) {
        Book aBook = (Book) item;
        return String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
    }

    @Override
    protected Integer length() {
        return 46;
    }
}
