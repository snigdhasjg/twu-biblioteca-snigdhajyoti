package com.biblioteca.menu;

import com.biblioteca.AccountManager;
import com.biblioteca.Library;
import com.biblioteca.account.AccountType;
import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.items.Book.book;
import static com.biblioteca.items.Movie.movie;
import static org.mockito.Mockito.*;

class MenuTest {
    private IO mockIO;
    private AccountManager accountManager;
    private IAccount anUserAccount;

    @BeforeEach
    void setUp() {
        mockIO = mock(IO.class);
        anUserAccount = mock(IAccount.class);
        accountManager = mock(AccountManager.class);
    }

    @Test
    void expectsToQuitIfAllUserTypeQuitInLowercase() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("quit");
        aMenu.options();

        verify(mockIO, times(1)).displayWithNewLine("\n\n.....................MENU.....................");
    }

    @Test
    void expectsToQuitIfAllUserTypeQuitInUppercase() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("QUIt");
        aMenu.options();

        verify(mockIO, times(1)).displayWithNewLine("\n\n.....................MENU.....................");
    }

    @Test
    void expectsDisplayingBeforeLoginMenuTwoTimes() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        when(accountManager.isLoggedIn()).thenReturn(false);
        aMenu.options();

        final int wantedNumberOfInvocations = 2;
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("\n\n.....................MENU.....................");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t1. List all books");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t2. List all movies");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t3. Log In");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\ttype \"quit\" to exit");
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("..............................................");
        verify(mockIO, times(wantedNumberOfInvocations)).display("Enter your choice: ");
    }


    @Test
    void expectsDisplayingAfterLoginMenuTwoTimes() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        IAccount mockAccount = mock(IAccount.class);
        when(mockAccount.getAccountType()).thenReturn(AccountType.customer);
        when(accountManager.currentUser()).thenReturn(mockAccount);
        aMenu.options();

        final int wantedNumberOfInvocations = 2;
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("\n\n.....................MENU.....................");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t1. List all books");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t2. Checkout book");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t3. Return book");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t4. List all movies");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t5. Checkout movie");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t6. Return movie");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\ttype \"quit\" to exit");
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("..............................................");
        verify(mockIO, times(wantedNumberOfInvocations)).display("Enter your choice: ");
    }

    @Test
    void expectsDisplayingAllBooksAvailableInLibraryAfterAndBeforeLogIn() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(accountManager.isLoggedIn()).thenReturn(false);
        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        IAccount mockAccount = mock(IAccount.class);
        when(mockAccount.getAccountType()).thenReturn(AccountType.customer);
        when(accountManager.currentUser()).thenReturn(mockAccount);
        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        verify(mockIO, times(2)).displayWithNewLine("Gitanjali            R N Tagore           1910");
        verify(mockIO, times(2)).displayWithNewLine("2 States             Chetan Bhagat        2004");
    }

    @Test
    void expectsErrorMessageWhenInvalidInput() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("invalid option", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Select a valid option!");
    }

    @Test
    void expectsABookDisappearWhenItHasCheckedOut() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(accountManager.isLoggedIn()).thenReturn(true);
        IAccount mockAccount = mock(IAccount.class);
        when(mockAccount.getAccountType()).thenReturn(AccountType.customer);
        when(accountManager.currentUser()).thenReturn(mockAccount);
        when(mockIO.readInputAsString()).thenReturn("1", "2", "gitanjali", "1", "quit");
        aMenu.options();

        verify(mockIO, times(2))
                .displayWithNewLine("2 States             Chetan Bhagat        2004");
        verify(mockIO, times(1))
                .displayWithNewLine("Gitanjali            R N Tagore           1910");
    }

    @Test
    void expectsACheckedOutBookToCheckInSuccessfully() {
        Menu aMenu = new Menu(mockIO, initializeTheBookLibrary(), initializeTheMovieLibrary(), accountManager);

        when(accountManager.isLoggedIn()).thenReturn(true);
        when(accountManager.currentUser()).thenReturn(anUserAccount);
        when(mockIO.readInputAsString()).thenReturn("2", "2 states", "3", "2 states", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Thank you for returning the book");
    }

    @Test
    void expectsToShowEmptyLibraryWhenThereIsNoBookWhileShowingTheList() {
        Menu aMenu = new Menu(mockIO, new Library(Collections.EMPTY_LIST), initializeTheMovieLibrary(), accountManager);

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    @Test
    void expectsToShowEmptyLibraryWhenThereIsNoBookWhileCheckingOut() {
        Menu aMenu = new Menu(mockIO, new Library(Collections.EMPTY_LIST), initializeTheMovieLibrary(), accountManager);

        when(accountManager.isLoggedIn()).thenReturn(true);
        IAccount mockAccount = mock(IAccount.class);
        when(mockAccount.getAccountType()).thenReturn(AccountType.customer);
        when(accountManager.currentUser()).thenReturn(mockAccount);
        when(mockIO.readInputAsString()).thenReturn("2", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    private Library initializeTheBookLibrary() {
        String book1_name = "2 States";
        Book book1 = book(book1_name, "Chetan Bhagat", 2004);
        final String book2_name = "Gitanjali";
        Book book2 = book(book2_name, "R N Tagore", 1910);
        return new Library(Arrays.asList(book1, book2));
    }

    private Library initializeTheMovieLibrary() {
        Movie movie1 = movie("The Social Network", "David Finche", 2010, 7.7);
        Movie movie2 = movie("URI", "Aditya Dhar", 2019, 9.1);
        return new Library(Arrays.asList(movie1, movie2));
    }
}