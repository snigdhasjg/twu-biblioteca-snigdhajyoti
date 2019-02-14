package com.biblioteca.io;

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

    @Test
    void expects_HorizonatalLine_ToPrint_Dashed_Line_Of_Length_5() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner(System.in);
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.horizontalLine(5);

        verify(mockOut).println("-----");
    }

    @Test
    void expects_HorizonatalLine_ToPrint_Dashed_Line_Of_Length_10() {
        PrintStream mockOut = mock(PrintStream.class);
        Scanner aScanner = new Scanner(System.in);
        ConsoleIO io = new ConsoleIO(mockOut, aScanner);

        io.horizontalLine(10);

        verify(mockOut).println("----------");
    }
}
