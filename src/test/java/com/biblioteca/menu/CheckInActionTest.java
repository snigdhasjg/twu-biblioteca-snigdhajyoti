package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.biblioteca.items.Book.book;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInActionTest {

    @Nested
    class BookCheckIn {
        @Test
        void expectsDisplayNameReturnBook() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkInOption = new CheckInAction(mockIO, mockLibrary, "book");

            assertEquals("Return book", checkInOption.displayName());
        }


        @Test
        void expectsUnsuccessfulMessageWhenBookNameIsWrongOrDoesNotBelongsToTheLibrary() {
            IO mockIO = mock(IO.class);
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book");

            when(mockIO.readInputAsString()).thenReturn("AbC");
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("That is not a valid book to return");
        }

        @Test
        void expectsSuccessfulMessageWhenEnteredBookBelongsToTheLibrary() {
            IO mockIO = mock(IO.class);
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            assertDoesNotThrow(() -> aLibrary.checkOut("HaJaBaRaLa"));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book");

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("Thank you for returning the book");
        }
    }

    @Nested
    class MovieCheckIn {

        @Test
        void expectsDisplayNameReturnMovie() {
            IO mockIO = mock(IO.class);
            Library mockLibrary = mock(Library.class);
            Actionable checkInOption = new CheckInAction(mockIO, mockLibrary, "movie");

            assertEquals("Return movie", checkInOption.displayName());
        }


        @Test
        void expectsUnsuccessfulMessageWhenMovieNameIsWrongOrDoesNotBelongsToTheLibrary() {
            IO mockIO = mock(IO.class);
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "movie");

            when(mockIO.readInputAsString()).thenReturn("AbC");
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("That is not a valid movie to return");
        }

        @Test
        void expectsSuccessfulMessageWhenEnteredMovieBelongsToTheLibrary() {
            IO mockIO = mock(IO.class);
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            assertDoesNotThrow(() -> aLibrary.checkOut("uri"));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "movie");

            when(mockIO.readInputAsString()).thenReturn("uri");
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("Thank you for returning the movie");
        }

    }
}