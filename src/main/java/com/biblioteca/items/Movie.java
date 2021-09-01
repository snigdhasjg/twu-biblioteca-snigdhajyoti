package com.biblioteca.items;

//Represents single a cinema film
public class Movie implements LibraryItem {
    private final String name;
    private final String director;
    private final int yearOfRelease;
    private final String rating;

    private Movie(String name, String director, int yearOfRelease, String rating) {
        this.name = name;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    public static Movie movie(String name, String director, int yearOfRelease, double rating) {
        return new Movie(name, director, yearOfRelease, "" + rating);
    }

    public static Movie movie(String name, String director, int yearOfRelease) {
        return new Movie(name, director, yearOfRelease, "unrated");
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
        return rating;
    }
}
