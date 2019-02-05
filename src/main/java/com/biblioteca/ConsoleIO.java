package com.biblioteca;

import java.io.PrintStream;
// Represents output of any stream
public class ConsoleIO implements IO {
    private final PrintStream outputStream;

    ConsoleIO(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void display(String message) {
        outputStream.println(message);
    }
}
