package com.biblioteca;

import com.biblioteca.account.IAccount;
import com.biblioteca.account.UserAccount;
import com.biblioteca.exception.InvalidItemNameException;
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
        Book book3 = book("DDD Red Book", "Vaughn", 2015);
        Book book4 = book("DDD Blue Book", "Eric", 2003);
        Library aBookLibrary = new Library(Arrays.asList(book1, book2, book3, book4));

        Movie movie1 = movie("The Social Network", "David Finche", 2010, 7.7);
        Movie movie2 = movie("URI", "Aditya Dhar", 2019, 9.1);
        Movie movie3 = movie("Abra ka dabra", "someone", 2050);
        Movie movie4 = movie("Endgame", "Russo brothers", 2018);
        Library aMovieLibrary = new Library(Arrays.asList(movie1, movie2, movie3, movie4));

        IAccount customer1 = UserAccount.customer("001-JOHN", "password", "John Doe", "jdoe@mail.com", "1234");
        IAccount customer2 = UserAccount.customer("002-ALEX", "password", "Alex Pandian", "alex@mail.com", "1234");
        IAccount librarian = UserAccount.librarian("001-XXXX", "password", "Admin", "admin@bibloiteca.com", "1234");

        AccountManager accountManager = new AccountManager(Arrays.asList(customer1, customer2, librarian));

        IO anIOStream = new ConsoleIO(System.out, new Scanner(System.in));
        Menu aMenu = new Menu(anIOStream, aBookLibrary, aMovieLibrary, accountManager);

        try {
            aBookLibrary.checkOut("DDD Red Book", customer1);
            aBookLibrary.checkOut("DDD Blue Book", customer1);

            aMovieLibrary.checkOut("Endgame", customer1);
        } catch (InvalidItemNameException ignored) {
            // Put some book into John account, because John is a billionaire
            // Also it will be easy to test
        }

        new App(anIOStream, aMenu).start();
    }
}
