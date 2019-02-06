package com.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.biblioteca.Book.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTest {
    @Test
    void expectsADisplayOfAListOfBooksPresentsInLibrary() {
        final String book1_name = "Head First JAVA";
        Book book1 = book(book1_name);
        final String book2_name = "Extreme Programming";
        Book book2 = book(book2_name);
        Library aLibrary = new Library(Arrays.asList(book1,book2));

        String expectedAllBookName = book1_name + '\n' + book2_name + '\n';

        String allBookName = aLibrary.returnAllBooksName();

        assertEquals(expectedAllBookName,allBookName);
    }

}
