package com.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

// Represents output of any stream
public class ConsoleIO implements IO {
    private final PrintStream outputStream;
    private final Scanner aScanner;

    ConsoleIO(PrintStream outputStream, Scanner aScanner) {
        this.outputStream = outputStream;
        this.aScanner = aScanner;
    }

    @Override
    public int readInputAsNumber() {
        return aScanner.nextInt();
    }

    @Override
    public String readInputAsString() {
        return aScanner.next();
    }

    @Override
    public void displayWithNewLine(String message) {
        outputStream.println(message);
    }

    @Override
    public void display(String message) {
        outputStream.print(message);
    }
}
