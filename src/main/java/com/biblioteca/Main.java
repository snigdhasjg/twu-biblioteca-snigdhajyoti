package com.biblioteca;

import com.biblioteca.io.*;
import com.biblioteca.items.Book;
import com.biblioteca.menu.Menu;

import java.util.Arrays;
import java.util.Scanner;

import static com.biblioteca.items.Book.book;

// Represents Library Management Engine
public class Main {
    public static void main(String[] args) {
        Book book1 = book("2 States", "Chetan Bhagat", 2004);
        Book book2 = book("Gitanjali", "R N Tagore", 1910);

        Library aLibrary = new Library(Arrays.asList(book1, book2));
        IO anIOStream = new ConsoleIO(System.out, new Scanner(System.in));
        Menu aMenu = new Menu(anIOStream, aLibrary);

        new App(anIOStream, aMenu).start();
    }
}
