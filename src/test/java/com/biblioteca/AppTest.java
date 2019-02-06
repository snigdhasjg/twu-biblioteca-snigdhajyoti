package com.biblioteca;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class AppTest {

    @Test
    void expectWelcomeMessageToBeWelcomeToBiblioteca() {
        IO mockConsole = mock(IO.class);
        App bibliotecaApp = new App(mockConsole);

        when(mockConsole.readInputAsString()).thenReturn("quit");
        bibliotecaApp.start();

        verify(mockConsole).displayWithNewLine("Welcome to Biblioteca");
    }

    @Test
    void expectsDisplayingAllBooksAvailableInLibrary() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO);

        Mockito.when(mockIO.readInputAsString()).thenReturn("1", "quit");
        bibliotecaApp.start();

        verify(mockIO).displayWithNewLine("1    2 States            Chetan Bhagat             2004");
        verify(mockIO).displayWithNewLine("2    Gitanjali           R N Tagore                1910");
    }

    @Test
    void expectsDisplayingMenuTwoTimes() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO);

        Mockito.when(mockIO.readInputAsString()).thenReturn("1", "quit");
        bibliotecaApp.start();

        final int wantedNumberOfInvocations = 2;
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine(".......................................................");
        verify(mockIO, times(wantedNumberOfInvocations)).displayWithNewLine("1. List All Books");
        verify(mockIO, times(wantedNumberOfInvocations)).display("Enter your choice: ");
    }
}