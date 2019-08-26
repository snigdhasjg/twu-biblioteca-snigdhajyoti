package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.account.IAccount;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.biblioteca.items.Book.*;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckOutActionTest {

    private IO mockIO;
    private AccountManager accountManager;
    private IAccount anUserAccount;

    @BeforeEach
    void setUp() {
        mockIO = mock(IO.class);
        anUserAccount = mock(IAccount.class);
        accountManager = mock(AccountManager.class);
    }

    @Nested
    class BookCheckOut {
        @Test
        void expectsDisplayNameCheckoutBook() {
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "book", accountManager);

            assertEquals("Checkout book", checkoutOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "book", accountManager);

            when(mockLibrary.isEmpty()).thenReturn(true);
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No book in Library");
        }

        @Test
        void expectsSuccessfulCheckout() {
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "book", accountManager);

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Thank you! Enjoy the book");
        }

        @Test
        void expectsMessageForWrongBookName() {
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "book", accountManager);

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaL");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("That book is not available");
        }
    }

    @Nested
    class MovieCheckOut {

        @Test
        void expectsDisplayNameCheckoutMovie() {
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "movie", accountManager);

            assertEquals("Checkout movie", checkoutOption.displayName());
        }

        @Test
        void expectsEmptyLibraryMessageWhenLibraryIsEmpty() {
            Library mockLibrary = mock(Library.class);
            Actionable checkoutOption = new CheckOutAction(mockIO, mockLibrary, "movie", accountManager);

            when(mockLibrary.isEmpty()).thenReturn(true);
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Sorry! No movie in Library");
        }

        @Test
        void expectsSuccessfulCheckout() {
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "movie", accountManager);

            when(mockIO.readInputAsString()).thenReturn("uri");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("Thank you! Enjoy the movie");
        }

        @Test
        void expectsMessageForWrongBookName() {
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "movie", accountManager);

            when(mockIO.readInputAsString()).thenReturn("uri...");
            checkoutOption.execute();

            verify(mockIO).displayWithNewLine("That movie is not available");
        }
    }

    @Test
    void expectToAddUserDetailInLibrary(){
        Library aLibrary = mock(Library.class);
        Actionable checkoutOption = new CheckOutAction(mockIO, aLibrary, "book", accountManager);

        String itemName = "HaJaBaRaLa";
        when(mockIO.readInputAsString()).thenReturn(itemName);
        when(accountManager.currentUser()).thenReturn(anUserAccount);
        checkoutOption.execute();

        assertDoesNotThrow(()->verify(aLibrary).checkOut(itemName,anUserAccount));
    }
}