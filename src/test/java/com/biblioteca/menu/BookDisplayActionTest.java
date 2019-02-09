package com.biblioteca.menu;

import com.biblioteca.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookDisplayActionTest {

    @Test
    void expectsDisplayNameListAllBook() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable listOption = new BookDisplayAction(mockIO, mockLibrary);

        assertEquals("List All Books", listOption.displayName());
    }

    @Test
    void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable listOption = new BookDisplayAction(mockIO, mockLibrary);

        when(mockLibrary.isEmpty()).thenReturn(true);
        listOption.execute();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    @Test
    void expectsListOfBooksWhenLibraryNotEmpty() {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable listOption = new BookDisplayAction(mockIO, aLibrary);

        listOption.execute();

        verify(mockIO).displayWithNewLine("Book Name            Author               Year");
        verify(mockIO).displayWithNewLine("----------------------------------------------");
        verify(mockIO).displayWithNewLine("HaJaBaRaLa           Sukumar Roy          1921");
    }

}