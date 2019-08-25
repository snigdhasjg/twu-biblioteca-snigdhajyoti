package com.biblioteca;

import com.biblioteca.io.ConsoleIO;
import com.biblioteca.io.IO;
import com.biblioteca.items.Book;
import com.biblioteca.items.Movie;
import com.biblioteca.menu.Menu;

import java.util.Arrays;
import java.util.Scanner;

import static com.biblioteca.items.Book.book;
import static com.biblioteca.items.Movie.movie;

// Represents Library Management Engine
public class Main {
    public static void main(String[] args) {
        Book book1 = book("2 States", "Chetan Bhagat", 2004);
        Book book2 = book("Gitanjali", "R N Tagore", 1910);
        Library aBookLibrary = new Library(Arrays.asList(book1, book2));

        Movie movie1 = movie("The Social Network","David Finche",2010,7.7);
        Movie movie2 = movie("URI","Aditya Dhar",2019,9.1);
        Movie movie3 = movie("Abra ka dabra", "someone", 2050);
        Library aMovieLibrary = new Library(Arrays.asList(movie1,movie2,movie3));

        IO anIOStream = new ConsoleIO(System.out, new Scanner(System.in));
        Menu aMenu = new Menu(anIOStream, aBookLibrary, aMovieLibrary);

        new App(anIOStream, aMenu).start();
    }
}
