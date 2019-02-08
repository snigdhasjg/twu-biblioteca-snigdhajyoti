package com.biblioteca;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.biblioteca.Book.book;
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
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("1. List All Books");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("2. Checkout");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("3. Return");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("type \"quit\" to exit");
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