package com.biblioteca;

class Book {
    private final String name;

    Book(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
