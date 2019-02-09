package com.biblioteca.menu;

import com.biblioteca.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.menu.Actionable;
import com.biblioteca.menu.CheckInAction;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInActionTest {

    @Test
    void expectsDisplayNameListAllBook() {
        IO mockIO = mock(IO.class);
        Library mockLibrary = mock(Library.class);
        Actionable checkInOption = new CheckInAction(mockIO, mockLibrary);

        assertEquals("Return", checkInOption.displayName());
    }

    @Test
    void expectsUnsuccessfulMessageWhenBookNameIsWrongOrDoesNotBelongsToTheLibrary() {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("AbC");
        checkInOption.execute();

        verify(mockIO).displayWithNewLine("That is not a valid book to return");
    }

    @Test
    void expectsSuccessfulMessageWhenEnteredBookBelongsToTheLibrary() {
        IO mockIO = mock(IO.class);
        Book aBook = Book.book("HaJaBaRaLa", "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        assertDoesNotThrow(()->aLibrary.checkOut("HaJaBaRaLa"));
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary);

        when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
        checkInOption.execute();

        verify(mockIO).displayWithNewLine("Thank you for returning the book");
    }
}