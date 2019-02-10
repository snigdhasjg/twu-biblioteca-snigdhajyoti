package com.biblioteca.menu;

import com.biblioteca.items.Book;
import com.biblioteca.Library;
import com.biblioteca.io.IO;
import com.biblioteca.items.LibraryItems;

import java.util.List;

class BookDisplayAction implements Actionable {
    private static final String BOOK_DETAILS_FORMAT = "%-20s %-20s %-4s";
    private static final String LIST_ALL_BOOKS = "List All Books";
    private static final String EMPTY_BOOK = "Sorry! No book in Library";
    private static final String BOOK_NAME = "Book Name";
    private static final String AUTHOR = "Author";
    private static final String YEAR = "Year";

    private final IO anIOStream;
    private final Library aLibrary;

    BookDisplayAction(IO anIOStream, Library aLibrary) {
        this.anIOStream = anIOStream;
        this.aLibrary = aLibrary;
    }

    @Override
    public void execute() {
        if (aLibrary.isEmpty()) {
            anIOStream.displayWithNewLine(EMPTY_BOOK);
            return;
        }
        List<LibraryItems> allItems = aLibrary.listOfAvailableItems();
        if(allItems.get(0) instanceof Book) {
            anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, YEAR));
            anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
            for (LibraryItems eachItem : allItems) {
                Book aBook = (Book) eachItem;
                String bookDetails = String.format(BOOK_DETAILS_FORMAT, aBook.title(), aBook.author(), aBook.year());
                anIOStream.displayWithNewLine(bookDetails);
            }
        }
    }

    @Override
    public String displayName() {
        return LIST_ALL_BOOKS;
    }
}
