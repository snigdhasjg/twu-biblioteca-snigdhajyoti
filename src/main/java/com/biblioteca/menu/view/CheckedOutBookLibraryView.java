package com.biblioteca.menu.view;

import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItem;

import java.util.Map;

public class CheckedOutBookLibraryView implements CheckedOutView {
    private static final String CHECKED_OUT_BOOK_HEADER = "%-20s %-20s %-8s";
    private static final String BOOK_NAME = "Book Name";
    private static final String USER_NAME = "User Name";
    private static final String LIBRARY_NUMBER = "Library Number";

    private final IO io;

    public CheckedOutBookLibraryView(IO io) {
        this.io = io;
    }

    @Override
    public String header() {
        return String.format(CHECKED_OUT_BOOK_HEADER, BOOK_NAME, USER_NAME, LIBRARY_NUMBER);
    }

    @Override
    public String details(LibraryItem item, IAccount anAccount) {
        Book aBook = (Book) item;
        return String.format(CHECKED_OUT_BOOK_HEADER, aBook.title(), anAccount.getName(), anAccount.getLibraryNumber());
    }

    @Override
    public Integer length() {
        return 57;
    }

    @Override
    public void display(Map<LibraryItem, IAccount> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        for (Map.Entry<LibraryItem, IAccount> entry : items.entrySet()) {
            String details = details(entry.getKey(), entry.getValue());
            io.displayWithNewLine(details);
        }
    }
}
