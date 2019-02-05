package com.biblioteca;

import org.junit.jupiter.api.Test;

import static com.biblioteca.Book.book;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void expectsBookNameToBeSame(){
        String bookName = "Head First JAVA";
        Book aBook = book(bookName);

        assertEquals(bookName,aBook.toString());
    }
}