package com.biblioteca.menu.view;

import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;

import java.util.List;

public abstract class LibraryView {
    private final IO io;

    public LibraryView(IO io) {
        this.io = io;
    }

    public void display(List<LibraryItem> items) {
        io.displayWithNewLine(header());
        io.horizontalLine(length());
        items.forEach(
                item -> io.displayWithNewLine(details(item))
        );
    }

    public abstract String contentType();

    protected abstract String header();

    protected abstract String details(LibraryItem item);

    protected abstract Integer length();
}
