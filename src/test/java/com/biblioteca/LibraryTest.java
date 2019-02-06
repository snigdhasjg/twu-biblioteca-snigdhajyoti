package com.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.Book.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTest {
    @Test
    void expectsADisplayOfAListOfBooksPresentsInLibrary() {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        List<Book> expectedBookList = Arrays.asList(book1, book2);

        Library aLibrary = new Library(expectedBookList);

        List<Book> allBooks = aLibrary.listOfAllBooks();

        assertEquals(expectedBookList, allBooks);
    }

}
