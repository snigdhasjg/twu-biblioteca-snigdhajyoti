package com.biblioteca;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

class ConsoleIOTest {
    @Test
    void expectsDisplayToPrintMessageHello() {
        PrintStream mockOut = mock(PrintStream.class);
        ConsoleIO io = new ConsoleIO(mockOut);

        io.display("Hello");

        verify(mockOut).println("Hello");
    }

}
