package com.biblioteca;

import java.util.List;

// Represents a place which have all books in in
class Library {

    private List<Book> listOfBooks;

    Library(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    List listOfAllBooks() {
        return listOfBooks;
    }
}
