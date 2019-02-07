package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all books in in
class Library {

    private List<Book> listOfAvailableBooks;

    Library(List<Book> listOfBooks) {
        this.listOfAvailableBooks = new ArrayList<>(listOfBooks);
    }

    List<Book> listOfAllBooks() {
        return listOfAvailableBooks;
    }

    void checkout(int bookNumber) {
        int bookIndex = bookNumber - 1;
        try {
            listOfAvailableBooks.remove(bookIndex);
        } catch (IndexOutOfBoundsException ignore) {
            throw new InvalidBookIndexException();
        }
    }

    void checkout(String bookName) {

    }
}
