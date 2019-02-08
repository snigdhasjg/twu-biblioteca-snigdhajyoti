package com.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.biblioteca.Book.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void expectsADisplayOfAListOfBooksPresentsInLibrary() {
        List<Book> expectedBookList = listOf2Books();

        Library aLibrary = new Library(expectedBookList);

        List<Book> allBooks = aLibrary.listOfAllBooks();

        assertEquals(expectedBookList, allBooks);
    }

    @Test
    void expectsToNumberOfBooks1When1BookHasCheckedOut() throws InvalidBookNameException {
        List<Book> initialBooks = listOf2Books();
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("gitanjali");
        List<Book> allAvailableBooks = aLibrary.listOfAllBooks();

        assertEquals(1, allAvailableBooks.size());
    }

    @Test
    void expectsBookNo1CheckedOut() throws InvalidBookNameException {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        List<Book> initialBooks = new ArrayList<>(Arrays.asList(book1, book2));
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("Head First JAVA");
        List<Book> allAvailableBooks = aLibrary.listOfAllBooks();
        initialBooks.remove(book1);

        assertEquals(initialBooks, allAvailableBooks);
    }

    @Test
    void expectsExceptionWhenThereIsNoBookInGivenIndex() {
        List<Book> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertThrows(InvalidBookNameException.class, () -> aLibrary.checkOut("some"));
    }

    @Test
    void expectsNoExceptionWhenThereIsNoBookInGivenName() {
        List<Book> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali"));
    }

    @Test
    void expectsNoExceptionWhenThereIsCheckInOfACheckedOutBook() {
        List<Book> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali"));
        assertDoesNotThrow(() -> aLibrary.checkIn("gitanjali"));
    }

    List<Book> listOf2Books() {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        return new ArrayList<>(Arrays.asList(book1, book2));
    }

}
