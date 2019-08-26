package com.biblioteca;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.exception.NotAMovieLibraryException;
import com.biblioteca.io.IO;
import com.biblioteca.menu.Menu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AppTest {

    @Test
    void expectWelcomeMessageToBeWelcomeToBiblioteca() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockConsole = mock(IO.class);
        Menu mockMenu = mock(Menu.class);
        App bibliotecaApp = new App(mockConsole);

        when(mockConsole.readInputAsString()).thenReturn("quit");
        bibliotecaApp.start();

        verify(mockConsole).displayWithNewLine("Welcome to Biblioteca");
    }

    @Test
    void expectsAllActionsIsDoneByMenu() throws NotABookLibraryException, NotAMovieLibraryException {
        IO mockIO = mock(IO.class);
        Menu mockMenu = mock(Menu.class);
        App bibliotecaApp = new App(mockIO);

        bibliotecaApp.start();
        verify(mockMenu).options();
    }
}