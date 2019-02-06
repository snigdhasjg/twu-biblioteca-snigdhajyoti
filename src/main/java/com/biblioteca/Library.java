package com.biblioteca;

import java.util.Set;
// Represents a place which have all books in in
class Library {

    private Set<Book> listOfBooks;

    Library(Set<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    Set<Book> listOfAllBooks(){
        return listOfBooks;
    }
}
