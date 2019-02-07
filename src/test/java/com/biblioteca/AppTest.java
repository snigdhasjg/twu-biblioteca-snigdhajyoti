package com.biblioteca;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static com.biblioteca.Book.book;
import static org.mockito.Mockito.*;

class AppTest {

    @Test
    void expectWelcomeMessageToBeWelcomeToBiblioteca() {
        IO mockConsole = mock(IO.class);
        App bibliotecaApp = new App(mockConsole, initializeTheLibrary());

        when(mockConsole.readInputAsString()).thenReturn("quit");
        bibliotecaApp.start();

        verify(mockConsole).displayWithNewLine("Welcome to Biblioteca");
    }

    @Test
    void expectsDisplayingAllBooksAvailableInLibrary() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO, initializeTheLibrary());

        Mockito.when(mockIO.readInputAsString()).thenReturn("1", "quit");
        bibliotecaApp.start();

        verify(mockIO).displayWithNewLine(String.format(App.BOOK_DETAILS_FORMAT, "1.", "2 States", "Chetan Bhagat", "2004"));
        verify(mockIO).displayWithNewLine(String.format(App.BOOK_DETAILS_FORMAT, "2.", "Gitanjali", "R N Tagore", "1910"));
    }

    @Test
    void expectsDisplayingMenuTwoTimes() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO, initializeTheLibrary());

        Mockito.when(mockIO.readInputAsString()).thenReturn("1", "quit");
        bibliotecaApp.start();

        final int wantedNumberOfInvocations = 2;
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("...............................MENU................................");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("1. List All Books");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("...................................................................");
        verify(mockIO, times(wantedNumberOfInvocations)).display("Enter your choice: ");
    }

    @Test
    void expectsErrorMessageWhenInvalidInput() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO, initializeTheLibrary());

        Mockito.when(mockIO.readInputAsString()).thenReturn("invalid option", "quit");
        bibliotecaApp.start();

        verify(mockIO).displayWithNewLine("Select a valid option!");
    }

    @Test
    void expectsABookDisappearWhenItHasCheckedOut() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO, initializeTheLibrary());

        Mockito.when(mockIO.readInputAsString()).thenReturn("2", "1", "quit");
        when(mockIO.readInputAsNumber()).thenReturn(1);
        bibliotecaApp.start();

        verify(mockIO, times(1)).displayWithNewLine(String.format(App.BOOK_DETAILS_FORMAT, "1.", "2 States", "Chetan Bhagat", "2004"));
        verify(mockIO, times(1)).displayWithNewLine(String.format(App.BOOK_DETAILS_FORMAT, "2.", "Gitanjali", "R N Tagore", "1910"));
        verify(mockIO, times(1)).displayWithNewLine(String.format(App.BOOK_DETAILS_FORMAT, "1.", "Gitanjali", "R N Tagore", "1910"));
    }

    private Library initializeTheLibrary() {
        final String book1_name = "2 States";
        Book book1 = book(book1_name, "Chetan Bhagat", 2004);
        final String book2_name = "Gitanjali";
        Book book2 = book(book2_name, "R N Tagore", 1910);
        return new Library(Arrays.asList(book1, book2));
    }
}