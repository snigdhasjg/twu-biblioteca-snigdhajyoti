package com.biblioteca.menu;

import com.biblioteca.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.menu.Actionable;
import com.biblioteca.menu.CheckOutAction;
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
    void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary);

        when(mockLibrary.isEmpty()).thenReturn(true);
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    @Test
    void expectsSuccessfulCheckout(){
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("Thank you! Enjoy the book");
    }

    @Test
    void expectsMessageForWrongBookName(){
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaL");
        checkoutOption.execute();

        verify(mockIO).displayWithNewLine("That book is not available");
    }
}