package com.biblioteca.menu.view;

import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItem;

import java.util.Map;

public class CheckedOutBookLibraryView extends CheckedOutView {
    private static final String CHECKED_OUT_BOOK_HEADER = "%-20s %-20s %-8s";
    private static final String BOOK_NAME = "Book Name";
    private static final String USER_NAME = "User Name";
    private static final String LIBRARY_NUMBER = "Library Number";

    public CheckedOutBookLibraryView(IO io) {
        super(io);
    }

    @Override
    protected String header() {
        return String.format(CHECKED_OUT_BOOK_HEADER, BOOK_NAME, USER_NAME, LIBRARY_NUMBER);
    }

    @Override
    protected String details(LibraryItem item, IAccount anAccount) {
        Book aBook = (Book) item;
        return String.format(CHECKED_OUT_BOOK_HEADER, aBook.title(), anAccount.getName(), anAccount.getLibraryNumber());
    }

    @Override
    protected Integer length() {
        return 57;
    }
}
