package com.biblioteca;

import java.util.List;

class Library {

    private List<Book> listOfBooks;

    Library(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    String returnAllBooksName(){
        String allBooksName = "";
        for(Book eachBook : listOfBooks){
            allBooksName += eachBook.toString() + '\n';
        }
        return allBooksName;
    }
}
