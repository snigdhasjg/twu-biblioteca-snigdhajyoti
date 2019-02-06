package com.biblioteca;

// Represents a single book
class Book {
    private final String name;
    private final String author;
    private final int yearOfPublication;

    static Book book(String name, String author, int yearOfPublication) {
        return new Book(name, author, yearOfPublication);
    }

    private Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    String title() {
        return name;
    }

    String author() {
        return author;
    }

    String year() {
        return "" + yearOfPublication;
    }
}
