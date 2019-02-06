package com.biblioteca;

// Represents a single book
class Book {
    private final String name;

    static Book book(String name){
        return new Book(name);
    }

    private Book(String name){
        this.name = name;
    }



    @Override
    public String toString() {
        return name;
    }
}
