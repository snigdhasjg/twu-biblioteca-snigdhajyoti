package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.items.Book.book;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class MenuTest {

    @Test
    void expectsToQuitIfAllUserTypeQuitInLowercase() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("quit");
        aMenu.options();

        verify(mockIO, times(1)).displayWithNewLine("\n.....................MENU.....................");
    }

    @Test
    void expectsToQuitIfAllUserTypeQuitInUppercase() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("QUIt");
        aMenu.options();

        verify(mockIO, times(1)).displayWithNewLine("\n.....................MENU.....................");
    }

    @Test
    void expectsDisplayingMenuTwoTimes() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        final int wantedNumberOfInvocations = 2;
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("\n.....................MENU.....................");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t1. List All Books");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t2. Checkout");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\t3. Return");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("\t\t\ttype \"quit\" to exit");
        verify(mockIO, times(wantedNumberOfInvocations))
                .displayWithNewLine("..............................................");
        verify(mockIO, times(wantedNumberOfInvocations)).display("Enter your choice: ");
    }

    @Test
    void expectsDisplayingAllBooksAvailableInLibrary() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Gitanjali            R N Tagore           1910");
        verify(mockIO).displayWithNewLine("2 States             Chetan Bhagat        2004");
    }

    @Test
    void expectsErrorMessageWhenInvalidInput() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("invalid option", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Select a valid option!");
    }

    @Test
    void expectsABookDisappearWhenItHasCheckedOut() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("1", "2", "gitanjali", "1", "quit");
        aMenu.options();

        verify(mockIO, times(2))
                .displayWithNewLine("2 States             Chetan Bhagat        2004");
        verify(mockIO, times(1))
                .displayWithNewLine("Gitanjali            R N Tagore           1910");
    }

    @Test
    void expectsACheckedOutBookToCheckInSuccessfully() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, initializeTheLibrary());

        when(mockIO.readInputAsString()).thenReturn("2", "2 states", "3", "2 states", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Thank you for returning the book");
    }

    @Test
    void expectsToShowEmptyLibraryWhenThereIsNoBookWhileShowingTheList() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, new Library(Collections.EMPTY_LIST));

        when(mockIO.readInputAsString()).thenReturn("1", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    @Test
    void expectsToShowEmptyLibraryWhenThereIsNoBookWhileCheckingOut() {
        IO mockIO = mock(IO.class);
        Menu aMenu = new Menu(mockIO, new Library(Collections.EMPTY_LIST));

        when(mockIO.readInputAsString()).thenReturn("2", "quit");
        aMenu.options();

        verify(mockIO).displayWithNewLine("Sorry! No book in Library");
    }

    private Library initializeTheLibrary() {
        final String book1_name = "2 States";
        Book book1 = book(book1_name, "Chetan Bhagat", 2004);
        final String book2_name = "Gitanjali";
        Book book2 = book(book2_name, "R N Tagore", 1910);
        return new Library(Arrays.asList(book1, book2));
    }
}