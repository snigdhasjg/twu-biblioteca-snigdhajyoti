package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInActionTest {

    @DisplayName("\uD83D\uDE00")
    @Test
    void expectsDisplayNameListAllBook() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable checkInOption = new CheckInAction(mockIO, mockLibrary);

        assertEquals("Return", checkInOption.displayName());
    }


    @Test
    void expectsUnsuccessfulMessageWhenBookNameIsWrongOrDoesNotBelongsToTheLibrary() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("AbC");
        checkInOption.execute();

        verify(mockIO).displayWithNewLine("That is not a valid item to return");
    }

    @Test
    void expectsSuccessfulMessageWhenEnteredBookBelongsToTheLibrary() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        assertDoesNotThrow(()->aLibrary.checkOut("HaJaBaRaLa"));
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
        checkInOption.execute();

        verify(mockIO).displayWithNewLine("Thank you for returning the item");
    }
}