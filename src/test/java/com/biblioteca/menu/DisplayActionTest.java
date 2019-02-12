package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisplayActionTest {

    @Nested
    class BookDisplay {

        @Test
        void expectsDisplayNameListAllBook() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable listOption = new DisplayAction(mockIO, mockLibrary, "book");

            assertEquals("List all books", listOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable listOption = new DisplayAction(mockIO, mockLibrary, "book");

            when(mockLibrary.isEmpty()).thenReturn(true);
            listOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No book in Library");
        }

        @Test
        void expectsListOfBooksWhenLibraryNotEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
            IO mockIO = mock(IO.class);
            Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable listOption = new DisplayAction(mockIO, aLibrary, "book");

            listOption.execute();

            verify(mockIO).displayWithNewLine("Book Name            Author               Year");
            verify(mockIO).displayWithNewLine("----------------------------------------------");
            verify(mockIO).displayWithNewLine("HaJaBaRaLa           Sukumar Roy          1921");
        }
    }

    @Nested
    class MovieDisplay {

        @Test
        void expectsDisplayNameListAllMovie() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable listOption = new DisplayAction(mockIO, mockLibrary, "movie");

            assertEquals("List all movies", listOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable listOption = new DisplayAction(mockIO, mockLibrary, "movie");

            when(mockLibrary.isEmpty()).thenReturn(true);
            listOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No movie in Library");
        }

        @Test
        void expectsListOfBooksWhenLibraryNotEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
            IO mockIO = mock(IO.class);
            Movie movie1 = movie("The Social Network", "David Finche", 2010, 7.7);
            Library aLibrary = new Library(Collections.singletonList(movie1));
            Actionable listOption = new DisplayAction(mockIO, aLibrary, "movie");

            listOption.execute();

            verify(mockIO).displayWithNewLine("Movie Name           Director             Year                 Rating ");
            verify(mockIO).displayWithNewLine("----------------------------------------------------------------------");
            verify(mockIO).displayWithNewLine("The Social Network   David Finche         2010                 7.7    ");
        }
    }

}