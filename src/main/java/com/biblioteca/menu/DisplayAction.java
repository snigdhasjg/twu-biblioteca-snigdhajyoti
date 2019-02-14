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
        List<LibraryItem> allItems = aLibrary.availableItems();
        if (isBook()) {
            display(allItems);
        }
        if (isMovie()) {
            display(allItems);
        }
    }

    private void display(List<LibraryItem> allItems) {
        anIOStream.displayWithNewLine(header());
        anIOStream.displayWithNewLine(something(length()));
        for (LibraryItem eachItem : allItems) {
            String movieDetails = details(eachItem);
            anIOStream.displayWithNewLine(movieDetails);
        }
    }

    private Integer length() {
        if(isBook()) {
            return  46;
        }
        if(isMovie()) {
            return  70;
        }
        return 0;
    }

    private String something(Integer length) {
        return new String(new char[length]).replace("\0", "-");
    }

    private String details(LibraryItem item) {
        if(isBook()) {
            Book aBook = (Book) item;
            return String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
        }
        if(isMovie()) {
            Movie aMovie = (Movie) item;
            return String.format(MOVIE_DETAILS_FORMAT, aMovie.title(), aMovie.director(), aMovie.year(), aMovie.rating());
        }
        return "";
    }

    private String header() {
        if(isBook()) {
            return String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, PUBLICATION_YEAR);
        }
        if(isMovie()) {
            return String.format(MOVIE_DETAILS_FORMAT, MOVIE_NAME, DIRECTOR, YEAR, RATING);
        }
        return "";
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
}
