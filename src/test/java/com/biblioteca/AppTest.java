package com.biblioteca;

import com.biblioteca.io.IO;
import com.biblioteca.menu.Menu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AppTest {

    @Test
    void expectWelcomeMessageToBeWelcomeToBiblioteca() {
        IO mockConsole = mock(IO.class);
        Menu mockMenu = mock(Menu.class);
        App bibliotecaApp = new App(mockConsole, mockMenu);

        when(mockConsole.readInputAsString()).thenReturn("quit");
        bibliotecaApp.start();

        verify(mockConsole).displayWithNewLine("Welcome to Biblioteca");
    }

    @Test
    void expectsAllActionsIsDoneByMenu() {
        IO mockIO = mock(IO.class);
        Menu mockMenu = mock(Menu.class);
        App bibliotecaApp = new App(mockIO, mockMenu);

        bibliotecaApp.start();
        verify(mockMenu).options();
    }
}