package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all books in in
class Library {

    private List<Book> listOfAvailableBooks;
    private List<Book> listOfCheckedOutBooks;

    Library(List<Book> listOfBooks) {
        this.listOfAvailableBooks = new ArrayList<>(listOfBooks);
        this.listOfCheckedOutBooks = new ArrayList<>();
    }

    List<Book> listOfAllBooks() {
        return listOfAvailableBooks;
    }

    void checkOut(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, listOfAvailableBooks);
        listOfAvailableBooks.remove(searchedBook);
        listOfCheckedOutBooks.add(searchedBook);
    }

    void checkIn(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, listOfCheckedOutBooks);
        listOfCheckedOutBooks.remove(searchedBook);
        listOfAvailableBooks.add(searchedBook);
    }

    boolean isEmpty() {
        return listOfAvailableBooks.size() == 0;
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
