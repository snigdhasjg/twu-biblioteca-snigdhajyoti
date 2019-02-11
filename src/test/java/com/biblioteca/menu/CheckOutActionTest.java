package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckOutActionTest {

    @Test
    void expectsDisplayNameCheckout() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary);

        assertEquals("Checkout", checkoutOption.displayName());
    }

    @Test
    void expectsEmptyLibraryMessageWhenLibraryIsEmpty() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary);

        when(mockLibrary.isEmpty()).thenReturn(true);
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("Sorry! No item in Library");
    }

    @Test
    void expectsSuccessfulCheckout() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("Thank you! Enjoy the item");
    }

    @Test
    void expectsMessageForWrongBookName() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaL");
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("That item is not available");
    }
}