package com.biblioteca;

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
        anIOStream.displayWithNewLine(String.format(BOOK_DETAILS_FORMAT, BOOK_NAME, AUTHOR, YEAR));
        anIOStream.displayWithNewLine(new String(new char[46]).replace("\0", "-"));
        List<Book> allBooks = aLibrary.listOfAvailableBooks();
        for (Book eachBook : allBooks) {
            String bookDetails = String.format(BOOK_DETAILS_FORMAT, eachBook.title(), eachBook.author(), eachBook.year());
            anIOStream.displayWithNewLine(bookDetails);
        }
    }

    @Override
    public String displayName() {
        return LIST_ALL_BOOKS;
    }
}
