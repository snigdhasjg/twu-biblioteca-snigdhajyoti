package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.biblioteca.items.Book.*;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckOutActionTest {

    @Nested
    class BookCheckOut {
        @Test
        void expectsDisplayNameCheckoutBook() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "book");

            assertEquals("Checkout book", checkoutOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "book");

            when(mockLibrary.isEmpty()).thenReturn(true);
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No book in Library");
        }

        @Test
        void expectsSuccessfulCheckout() {
            IO mockIO = mock(IO.class);
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "book");

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Thank you! Enjoy the book");
        }

        @Test
        void expectsMessageForWrongBookName() {
            IO mockIO = mock(IO.class);
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "book");

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaL");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("That book is not available");
        }
    }

    @Nested
    class MovieCheckOut {

        @Test
        void expectsDisplayNameCheckoutMovie() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "movie");

            assertEquals("Checkout movie", checkoutOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "movie");

            when(mockLibrary.isEmpty()).thenReturn(true);
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No movie in Library");
        }

        @Test
        void expectsSuccessfulCheckout() {
            IO mockIO = mock(IO.class);
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "movie");

            when(mockIO.readInputAsString()).thenReturn("uri");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Thank you! Enjoy the movie");
        }

        @Test
        void expectsMessageForWrongBookName() {
            IO mockIO = mock(IO.class);
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "movie");

            when(mockIO.readInputAsString()).thenReturn("uri...");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("That movie is not available");
        }
    }
}