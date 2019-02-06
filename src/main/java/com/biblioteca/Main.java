package com.biblioteca;

import java.util.Arrays;
import java.util.Scanner;

import static com.biblioteca.Book.book;

// Represents Library Management Engine
public class Main {
    public static void main(String[] args) {
        final String book1_name = "2 States";
        Book book1 = book(book1_name, "Chetan Bhagat", 2004);
        final String book2_name = "Gitanjali";
        Book book2 = book(book2_name, "R N Tagore", 1910);

        IO aOutPutStream = new ConsoleIO(System.out, new Scanner(System.in));
        new App(aOutPutStream, Arrays.asList(book1, book2)).start();
    }
}
