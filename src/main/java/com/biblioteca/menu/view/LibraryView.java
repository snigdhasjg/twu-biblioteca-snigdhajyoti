package com.biblioteca.menu.view;

import com.biblioteca.items.LibraryItem;

import java.util.List;

public interface LibraryView {
    String header();

    String details(LibraryItem item);

    Integer length();

    String contentType();

    void display(List<LibraryItem> items);
}
