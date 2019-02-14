package com.biblioteca.items;

//Represents a written or printed work consisting of pages glued or sewn together along one side and bound in covers
public class Book implements LibraryItem {
    private final String name;
    private final String author;
    private final int yearOfPublication;

    public static Book book(String name, String author, int yearOfPublication) {
        return new Book(name, author, yearOfPublication);
    }

    private Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String title() {
        return name;
    }

    public String author() {
        return author;
    }

    public String year() {
        return "" + yearOfPublication;
    }
}
