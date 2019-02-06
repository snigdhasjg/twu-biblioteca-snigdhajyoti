package com.biblioteca;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

class ConsoleIOTest {
    @Test
    void expectsDisplayToPrintMessageHello() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner(System.in);
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.displayWithNewLine("Hello");

        verify(mockOut).println("Hello");
    }

}
