package com.biblioteca.menu;

import com.biblioteca.exception.NotABookLibraryException;
import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItems;

import java.util.List;

class BookDisplayAction implements Actionable {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String LIST_ALL_BOOKS = "List All Books";
    private static final String EMPTY_BOOK_LIBRARY = "Sorry! No book in Library";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String YEAR = "Year";

    private final IO anIOStream;
    private final Library aBookLibrary;

    BookDisplayAction(IO anIOStream, Library aBookLibrary) {
        this.anIOStream = anIOStream;
        this.aBookLibrary = aBookLibrary;
    }

    @Override
    public void execute() throws NotABookLibraryException {
        if (aBookLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_BOOK_LIBRARY);
            return;
        }
        List<LibraryItems> allItems = aBookLibrary.listOfAvailableItems();
        if(allItems.get(0) instanceof Book) {
            anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, YEAR));
            anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
            for (LibraryItems eachItem : allItems) {
                Book aBook = (Book) eachItem;
                String bookDetails = String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
                anIOStream.displayWithNewLine(bookDetails);
            }
            return;
        }
        throw new NotABookLibraryException();
    }

    @Override
    public String displayName() {
        return LIST_ALL_BOOKS;
    }
}
