package com.biblioteca.menu;

import com.biblioteca.items.LibraryItem;

public interface View {
    String header();

    String details(LibraryItem item);

    Integer length();
}
