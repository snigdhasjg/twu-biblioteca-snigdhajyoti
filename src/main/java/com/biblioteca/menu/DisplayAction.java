package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItem;
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
    private BookView bookView;
    private MovieView movieView;

    DisplayAction(IO anIOStream, Library aLibrary, String contentType) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
        this.contentType = contentType;
        bookView = new BookView();
        movieView = new MovieView();
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(String.format(EMPTY_LIBRARY, contentType));
            return;
        }
        List<LibraryItem> availableItems = aLibrary.availableItems();
        if (isBook()) {
            bookView.display(availableItems, anIOStream);
        }
        if (isMovie()) {
            movieView.display(availableItems, anIOStream);
        }
    }

    private boolean isBook() {
        return contentType.equalsIgnoreCase("book");
    }

    private boolean isMovie() {
        return contentType.equalsIgnoreCase("movie");
    }

    @Override
    public String displayName() {
        return String.format(LIST_ALL, contentType);
    }

    private class BookView implements View {
        public String header() {
            return String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        }

        public String details(LibraryItem item) {
            Book aBook = (Book) item;
            return String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
        }

        public Integer length() {
            return 46;
        }

        private void display(List<LibraryItem> items, IO io) {
            io.displayWithNewLine(header());
            io.horizontalLine(length());
            for (LibraryItem item : items) {
                String details = details(item);
                io.displayWithNewLine(details);
            }
        }
    }

    private class MovieView implements View {
        public String header() {
            return String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING);
        }

        public String details(LibraryItem item) {
            Movie aMovie = (Movie) item;
            return String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
        }

        public Integer length() {
            return 70;
        }

        private void display(List<LibraryItem> items, IO io) {
            io.displayWithNewLine(header());
            io.horizontalLine(length());
            for (LibraryItem item : items) {
                String details = details(item);
                io.displayWithNewLine(details);
            }
        }
    }
}
