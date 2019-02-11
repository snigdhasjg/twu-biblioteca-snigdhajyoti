package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
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
    void expectsEmptyLibraryMessageWhenLibraryIsEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable listOption = new BookDisplayAction(mockIO, mockLibrary);

        when(mockLibrary.isEmpty()).thenReturn(true);
        listOption.execute();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    @Test
    void expectsListOfBooksWhenLibraryNotEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable listOption = new BookDisplayAction(mockIO, aLibrary);

        listOption.execute();

        verify(mockIO).displayWithNewLine("Book Name            Author               Year");
        verify(mockIO).displayWithNewLine("----------------------------------------------");
        verify(mockIO).displayWithNewLine("HaJaBaRaLa           Sukumar Roy          1921");
    }

    @Test
    void expectsExceptionWhenLibraryIsNotABookLibrary() {
        IO mockIO = mock(IO.class);
        Movie aMovie = Movie.movie("The Social Network", "David Finche", 2010, 7.7);
        Library aLibrary = new Library(Collections.singletonList(aMovie));
        Actionable listOption = new BookDisplayAction(mockIO, aLibrary);

        assertThrows(NotABookLibraryException.class, listOption::execute);
    }

}