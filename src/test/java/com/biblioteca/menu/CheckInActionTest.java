package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.account.IAccount;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.exception.UserDoesNotMatchException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Collections;

import static com.biblioteca.items.Book.book;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInActionTest {
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
    class BookCheckIn {

        @Test
        void expectsDisplayNameReturnBook() {
            Library mockLibrary = mock(Library.class);
            Actionable checkInOption = new CheckInAction(mockIO, mockLibrary, "book", accountManager);

            assertEquals("Return book", checkInOption.displayName());
        }

        @Test
        void expectsUnsuccessfulMessageWhenBookNameIsWrongOrDoesNotBelongsToTheLibrary() {
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book", accountManager);

            when(mockIO.readInputAsString()).thenReturn("AbC");

            checkInOption.execute();

            verify(mockIO).displayWithNewLine("That is not a valid book to return");
        }

        @Test
        void expectsSuccessfulMessageWhenEnteredBookBelongsToTheLibrary() {
            Book aBook = book("HaJaBaRaLa", "Sukumar Roy", 1921);
            Library aLibrary = new Library(Collections.singletonList(aBook));
            assertDoesNotThrow(() -> aLibrary.checkOut("HaJaBaRaLa", anUserAccount));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book", accountManager);

            when(mockIO.readInputAsString()).thenReturn("HaJaBaRaLa");
            when(accountManager.currentUser()).thenReturn(anUserAccount);
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("Thank you for returning the book");
        }
    }

    @Nested
    class MovieCheckIn {

        @Test
        void expectsDisplayNameReturnMovie() {
            Library mockLibrary = mock(Library.class);
            Actionable checkInOption = new CheckInAction(mockIO, mockLibrary, "movie", accountManager);

            assertEquals("Return movie", checkInOption.displayName());
        }

        @Test
        void expectsUnsuccessfulMessageWhenMovieNameIsWrongOrDoesNotBelongsToTheLibrary() {
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "movie", accountManager);

            when(mockIO.readInputAsString()).thenReturn("AbC");
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("That is not a valid movie to return");
        }

        @Test
        void expectsSuccessfulMessageWhenEnteredMovieBelongsToTheLibrary() {
            Movie aMovie = movie("URI", "Aditya Dhar", 2019, 9.1);
            Library aLibrary = new Library(Collections.singletonList(aMovie));
            assertDoesNotThrow(() -> aLibrary.checkOut("uri", anUserAccount));
            Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "movie", accountManager);

            when(mockIO.readInputAsString()).thenReturn("uri");
            when(accountManager.currentUser()).thenReturn(anUserAccount);
            checkInOption.execute();

            verify(mockIO).displayWithNewLine("Thank you for returning the movie");
        }
    }

    @Test
    void expectToAddUserDetailInLibrary(){
        Library aLibrary = mock(Library.class);
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book", accountManager);

        String itemName = "HaJaBaRaLa";
        when(mockIO.readInputAsString()).thenReturn(itemName);
        when(accountManager.currentUser()).thenReturn(anUserAccount);
        checkInOption.execute();

        assertDoesNotThrow(()->verify(aLibrary).checkIn(itemName,anUserAccount));
    }

    @Test
    void expectUserMismatchException() {
        String bookName = "HaJaBaRaLa";
        Book aBook = book(bookName, "Sukumar Roy", 1921);
        Library aLibrary = new Library(Collections.singletonList(aBook));
        Actionable checkInOption = new CheckInAction(mockIO, aLibrary, "book", accountManager);

        assertDoesNotThrow(()->aLibrary.checkOut(bookName, anUserAccount));
        IAccount otherUser = mock(IAccount.class);
        when(accountManager.currentUser()).thenReturn(otherUser);
        when(mockIO.readInputAsString()).thenReturn(bookName);
        checkInOption.execute();

        verify(mockIO).displayWithNewLine("This book doesn't belongs to you");
    }
}