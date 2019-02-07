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

    void checkout(String bookName) throws InvalidBookNameException{
        Book searchedBook = searchBook(bookName);
        listOfAvailableBooks.remove(searchedBook);
    }

    private Book searchBook(String bookName) throws InvalidBookNameException{
        for(Book eachBook : listOfAvailableBooks){
            String eachBookTitle = eachBook.title();
            if(eachBookTitle.equalsIgnoreCase(bookName)){
                return eachBook;
            }
        }
        throw new InvalidBookNameException();
    }

    boolean isEmpty() {
        return listOfAvailableBooks.size() == 0;
    }
}
