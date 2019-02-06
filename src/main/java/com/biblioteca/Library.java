package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all books in in
class Library {

    private List<Book> listOfBooks;

    Library(List<Book> listOfBooks) {
        this.listOfBooks = new ArrayList<>(listOfBooks);
    }

    List<Book> listOfAllBooks() {
        return listOfBooks;
    }
}
