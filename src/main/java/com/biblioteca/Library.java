package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all books in it
class Library {

    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    Library(List<Book> listOfBooks) {
        this.availableBooks = new ArrayList<>(listOfBooks);
        this.checkedOutBooks = new ArrayList<>();
    }

    List<Book> listOfAvailableBooks() {
        return availableBooks;
    }

    void checkOut(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, availableBooks);
        availableBooks.remove(searchedBook);
        checkedOutBooks.add(searchedBook);
    }

    void checkIn(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, checkedOutBooks);
        checkedOutBooks.remove(searchedBook);
        availableBooks.add(searchedBook);
    }

    boolean isEmpty() {
        return availableBooks.size() == 0;
    }

    private Book searchBook(String bookName, List<Book> someGroupOfBook) throws InvalidBookNameException {
        for (Book eachBook : someGroupOfBook) {
            String eachBookTitle = eachBook.title();
            if (eachBookTitle.equalsIgnoreCase(bookName)) {
                return eachBook;
            }
        }
        throw new InvalidBookNameException();
    }
}
