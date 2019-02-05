package com.biblioteca;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AppTest {

    @Test
    void expectWelcomeMessageToBeWelcomeToBiblioteca() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO);

        bibliotecaApp.start();

        verify(mockIO).display("Welcome to Biblioteca");
    }

    @Test
    void expectsDisplayingAllBooksAvailableInLibrary() {
        IO mockIO = mock(IO.class);
        App bibliotecaApp = new App(mockIO);

        bibliotecaApp.start();

        verify(mockIO).display("Welcome to Biblioteca");
        verify(mockIO).display("Head First JAVA\nExtreme Programming\n");
    }

}