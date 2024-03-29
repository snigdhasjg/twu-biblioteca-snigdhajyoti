package com.biblioteca.io;

import java.io.PrintStream;
import java.util.Scanner;

// Represents output of any stream
public class ConsoleIO implements IO {
    private final PrintStream outputStream;
    private final Scanner aScanner;

    public ConsoleIO(PrintStream outputStream, Scanner aScanner) {
        this.outputStream = outputStream;
        this.aScanner = aScanner;
    }

    @Override
    public int readInputAsNumber() {
        return aScanner.nextInt();
    }

    @Override
    public String readInputAsString() {
        return aScanner.nextLine();
    }

    @Override
    public void displayWithNewLine(String message) {
        outputStream.println(message);
    }

    @Override
    public void display(String message) {
        outputStream.print(message);
    }

    @Override
    public void horizontalLine(Integer length) {
        String dashedLine = new String(new char[length]).replace("\0", "-");
        outputStream.println(dashedLine);
    }
}
