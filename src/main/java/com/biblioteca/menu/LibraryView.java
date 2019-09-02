package com.biblioteca.menu;

import com.biblioteca.items.LibraryItem;

import java.util.List;

public interface LibraryView {
    String header();

    String details(LibraryItem item);

    Integer length();

    void display(List<LibraryItem> items);
}
