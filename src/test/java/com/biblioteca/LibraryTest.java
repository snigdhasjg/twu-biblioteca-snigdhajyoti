package com.biblioteca;

import com.biblioteca.account.IAccount;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.Book;
import com.biblioteca.items.LibraryItem;
import com.biblioteca.items.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.biblioteca.items.Book.*;
import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibraryTest {
    private AccountManager accountManager;
    private IAccount anUserAccount;

    @BeforeEach
    void setUp() {
        accountManager = mock(AccountManager.class);
        anUserAccount = mock(IAccount.class);
    }

    @Test
    void expectsADisplayOfAListOfBooksPresentsInLibrary() {
        List<LibraryItem> expectedBookList = listOf2Books();

        Library aLibrary = new Library(expectedBookList);

        List<LibraryItem> allBooks = aLibrary.availableItems();

        assertEquals(expectedBookList, allBooks);
    }

    @Test
    void expectsToNumberOfBooks1When1BookHasCheckedOut() throws InvalidItemNameException {
        List<LibraryItem> initialBooks = listOf2Books();
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("gitanjali", accountManager.currentUser());
        List<LibraryItem> allAvailableBooks = aLibrary.availableItems();

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

        List<LibraryItem> initialBooks = new ArrayList<>(Arrays.asList(book1, book2));
        Library aLibrary = new Library(initialBooks);

        aLibrary.checkOut("Head First JAVA", accountManager.currentUser());
        List<LibraryItem> allAvailableBooks = aLibrary.availableItems();
        initialBooks.remove(book1);

        assertEquals(initialBooks, allAvailableBooks);
    }

    @Test
    void expectsExceptionWhenThereIsNoBookInGivenIndex() {
        List<LibraryItem> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertThrows(InvalidItemNameException.class, () -> aLibrary.checkOut("some", accountManager.currentUser()));
    }

    @Test
    void expectsNoExceptionWhenThereIsNoBookInGivenName() {
        List<LibraryItem> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali", accountManager.currentUser()));
    }

    @Test
    void expectsNoExceptionWhenThereIsCheckInOfACheckedOutBook() {
        List<LibraryItem> initialBook = listOf2Books();
        Library aLibrary = new Library(initialBook);

        when(accountManager.currentUser()).thenReturn(anUserAccount);
        assertDoesNotThrow(() -> aLibrary.checkOut("gitanjali", accountManager.currentUser()));
        assertDoesNotThrow(() -> aLibrary.checkIn("gitanjali", accountManager.currentUser()));
    }

    @Test
    void expectsADisplayOfAListOfMoviesPresentsInLibrary() {
        List<LibraryItem> expectedBookList = listOf2Movies();

        Library aLibrary = new Library(expectedBookList);

        List<LibraryItem> allMovies = aLibrary.availableItems();

        assertEquals(expectedBookList, allMovies);
    }

    @Test
    void expectsToNumberOfMovies1When1MovieHasCheckedOut() throws InvalidItemNameException {
        List<LibraryItem> initialMovies = listOf2Movies();
        Library aLibrary = new Library(initialMovies);

        aLibrary.checkOut("uri", accountManager.currentUser());
        List<LibraryItem> allAvailableMovies = aLibrary.availableItems();

        assertEquals(1, allAvailableMovies.size());
    }

    @Test
    void expectsMovieNo1CheckedOut() throws InvalidItemNameException {
        Movie movie1 = movie("The Social Network","David Finche",2010,7.7);
        Movie movie2 = movie("URI","Aditya Dhar",2019,9.1);

        List<LibraryItem> initialMovies = new ArrayList<>(Arrays.asList(movie1, movie2));
        Library aLibrary = new Library(initialMovies);

        aLibrary.checkOut("the social network", accountManager.currentUser());
        List<LibraryItem> allAvailableBooks = aLibrary.availableItems();
        initialMovies.remove(movie1);

        assertEquals(initialMovies, allAvailableBooks);
    }

    private List<LibraryItem> listOf2Movies(){
        Movie movie1 = movie("The Social Network","David Finche",2010,7.7);
        Movie movie2 = movie("URI","Aditya Dhar",2019,9.1);

        return new ArrayList<>(Arrays.asList(movie1,movie2));
    }

    private List<LibraryItem> listOf2Books() {
        final String book1_name = "Head First JAVA";
        final String book1Author = "someone";
        Book book1 = book(book1_name, book1Author, 2018);

        final String book2_name = "Gitanjali";
        final String book2Author = "R N Tagore";
        Book book2 = book(book2_name, book2Author, 1910);

        return new ArrayList<>(Arrays.asList(book1, book2));
    }

}
