package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all books in it
public class Library {

    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public Library(List<Book> listOfBooks) {
        this.availableBooks = new ArrayList<>(listOfBooks);
        this.checkedOutBooks = new ArrayList<>();
    }

    public List<Book> listOfAvailableBooks() {
        return availableBooks;
    }

    public void checkOut(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, availableBooks);
        availableBooks.remove(searchedBook);
        checkedOutBooks.add(searchedBook);
    }

    public void checkIn(String bookName) throws InvalidBookNameException {
        Book searchedBook = searchBook(bookName, checkedOutBooks);
        checkedOutBooks.remove(searchedBook);
        availableBooks.add(searchedBook);
    }

    public boolean isEmpty() {
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
