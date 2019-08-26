package com.biblioteca.io;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

class ConsoleIOTest {
    @Test
    void expectsDisplayToPrintMessageHello() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner("");
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.displayWithNewLine("Hello");

        verify(mockOut).println("Hello");
    }

    @Test
    void expectsHorizonatalLineToPrintDashedLineOfLength5() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner("");
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.horizontalLine(5);

        verify(mockOut).println("-----");
    }

    @Test
    void expectsHorizonatalLineToPrintDashedLineOfLength10() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner("");
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.horizontalLine(10);

        verify(mockOut).println("----------");
    }
}
