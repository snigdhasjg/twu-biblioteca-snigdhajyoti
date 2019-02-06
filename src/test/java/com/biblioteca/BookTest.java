package com.biblioteca;

import org.junit.jupiter.api.Test;

import static com.biblioteca.Book.book;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void expectsBookNameToBeSame() {
        String bookName = "Head First JAVA";
        String author = "Someone";
        int yearOfPublication = 2018;
        Book aBook = book(bookName, author, yearOfPublication);

        assertEquals(bookName, aBook.title());
        assertEquals(author, aBook.author());
        assertEquals("" + yearOfPublication, aBook.year());
    }
}