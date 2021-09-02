package com.biblioteca.menu.view;

import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;

import java.util.Map;

public abstract class CheckedOutView {
    private final IO io;

    public CheckedOutView(IO io) {
        this.io = io;
    }

    public final void display(Map<LibraryItem, IAccount> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        items.forEach(
                (key, value) -> io.displayWithNewLine(details(key, value))
        );
    }

    protected abstract String header();

    protected abstract String details(LibraryItem item, IAccount anAccount);

    protected abstract Integer length();
}
