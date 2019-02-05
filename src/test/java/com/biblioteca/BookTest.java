package com.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void expectsBookNameToBeSame(){
        String bookName = "Head First JAVA";
        Book aBook = new Book(bookName);

        assertEquals(bookName,aBook.toString());
    }
}