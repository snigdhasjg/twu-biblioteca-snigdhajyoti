package com.biblioteca.items;

//Represents single a cinema film
public class Movie implements LibraryItems {
    private final String name;
    private final String director;
    private final int yearOfRelease;
    private final double rating;

    public static Movie movie(String name, String director, int yearOfRelease, double rating) {
        return new Movie(name, director, yearOfRelease, rating);
    }

    private Movie(String name, String director, int yearOfRelease, double rating) {
        this.name = name;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    @Override
    public String title() {
        return name;
    }

    public String director() {
        return director;
    }

    public String year() {
        return "" + yearOfRelease;
    }

    public String rating() {
        return "" + rating;
    }
}
