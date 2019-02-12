package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItems;
import com.biblioteca.items.Movie;

import java.util.List;

class DisplayAction implements Actionable {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String MOVIE_DETAILS_FORMAT = "%-20s %-20s %-20s %-7s";
    private static final String LIST_ALL = "List all %ss";
    private static final String EMPTY_LIBRARY = "Sorry! No %s in Library";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String PUBLICATION_YEAR = "Year";
    private static final String MOVIE_NAME = "Movie Name";
    private static final String DIRECTOR = "Director";
    private static final String YEAR = "Year";
    private static final String RATING = "Rating";

    private final IO anIOStream;
    private final Library aLibrary;
    private final String contentType;

    DisplayAction(IO anIOStream, Library aLibrary, String contentType) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(String.format(EMPTY_LIBRARY, contentType));
            return;
        }
        List<LibraryItems> allItems = aLibrary.listOfAvailableItems();
        if (contentType.equalsIgnoreCase("book")) {
            anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, PUBLICATION_YEAR));
            anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
            for (LibraryItems eachItem : allItems) {
                Book aBook = (Book) eachItem;
                String bookDetails = String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
                anIOStream.displayWithNewLine(bookDetails);
            }
        }
        if (contentType.equalsIgnoreCase("movie")) {
            anIOStream.displayWithNewLine(String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING));
            anIOStream.displayWithNewLine(new String(new char[70]).replace("\0", "-"));
            for (LibraryItems eachItem : allItems) {
                Movie aMovie = (Movie) eachItem;
                String movieDetails = String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
                anIOStream.displayWithNewLine(movieDetails);
            }
        }
    }

    @Override
    public String displayName() {
        return String.format(LIST_ALL, contentType);
    }
}
