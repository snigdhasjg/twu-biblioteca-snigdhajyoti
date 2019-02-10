package com.biblioteca;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItems;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.biblioteca.items.Book.*;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void expectsADisplayOfAListOfBooksPresentsInLibrary() {
        List<LibraryItems> expectedBookList = listOf2Books();

        Library aLibrary = new Library(expectedBookList);

        List<LibraryItems> allBooks = aLibrary.listOfAvailableItems();

        assertEquals(expectedBookList, allBooks);
    }

    @Test
    void expectsToNumberOfBooks1When1BookHasCheckedOut() throws InvalidItemNameException {
        List<LibraryItems> initialBooks = listOf2Books();
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("gitanjali");
        List<LibraryItems> allAvailableBooks = aLibrary.listOfAvailableItems();

        assertEquals(1, allAvailableBooks.size());
    }

    @Test
    void expectsBookNo1CheckedOut() throws InvalidItemNameException {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        List<LibraryItems> initialBooks = new ArrayList<>(Arrays.asList(book1, book2));
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("Head First JAVA");
        List<LibraryItems> allAvailableBooks = aLibrary.listOfAvailableItems();
        initialBooks.remove(book1);

        assertEquals(initialBooks, allAvailableBooks);
    }

    @Test
    void expectsExceptionWhenThereIsNoBookInGivenIndex() {
        List<LibraryItems> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertThrows(InvalidItemNameException.class, () -> aLibrary.checkOut("some"));
    }

    @Test
    void expectsNoExceptionWhenThereIsNoBookInGivenName() {
        List<LibraryItems> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali"));
    }

    @Test
    void expectsNoExceptionWhenThereIsCheckInOfACheckedOutBook() {
        List<LibraryItems> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali"));
        assertDoesNotThrow(() -> aLibrary.checkIn("gitanjali"));
    }

    @Test
    void expectsADisplayOfAListOfMoviesPresentsInLibrary() {
        List<LibraryItems> expectedBookList = listOf2Movies();

        Library aLibrary = new Library(expectedBookList);

        List<LibraryItems> allMovies = aLibrary.listOfAvailableItems();

        assertEquals(expectedBookList, allMovies);
    }

    @Test
    void expectsToNumberOfMovies1When1MovieHasCheckedOut() throws InvalidItemNameException {
        List<LibraryItems> initialMovies = listOf2Movies();
        Library aLibrary = new Library(initialMovies);

        aLibrary.checkOut("uri");
        List<LibraryItems> allAvailableMovies = aLibrary.listOfAvailableItems();

        assertEquals(1, allAvailableMovies.size());
    }

    @Test
    void expectsMovieNo1CheckedOut() throws InvalidItemNameException {
        Movie movie1 = movie("The Social Network","David Finche",2010,7.7);
        Movie movie2 = movie("URI","Aditya Dhar",2019,9.1);

        List<LibraryItems> initialMovies = new ArrayList<>(Arrays.asList(movie1, movie2));
        Library aLibrary = new Library(initialMovies);

        aLibrary.checkOut("the social network");
        List<LibraryItems> allAvailableBooks = aLibrary.listOfAvailableItems();
        initialMovies.remove(movie1);

        assertEquals(initialMovies, allAvailableBooks);
    }

    private List<LibraryItems> listOf2Movies(){
        Movie movie1 = movie("The Social Network","David Finche",2010,7.7);
        Movie movie2 = movie("URI","Aditya Dhar",2019,9.1);

        return new ArrayList<>(Arrays.asList(movie1,movie2));
    }

    private List<LibraryItems> listOf2Books() {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        return new ArrayList<>(Arrays.asList(book1, book2));
    }

}
