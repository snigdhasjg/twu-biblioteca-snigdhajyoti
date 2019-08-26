package com.biblioteca.items;

import org.junit.jupiter.api.Test;

import static com.biblioteca.items.Movie.movie;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @Test
    void expectsMovieNameToBeSame() {
        String movieName = "The Social Network";
        String director = "David Fincher";
        int yearOfRelease = 2010;
        double rating = 7.7;
        Movie aMovie = movie(movieName, director, yearOfRelease, rating);

        assertEquals(movieName, aMovie.title());
        assertEquals(director, aMovie.director());
        assertEquals("" + yearOfRelease, aMovie.year());
        assertEquals("" + rating, aMovie.rating());
    }

    @Test
    void expectMovieRatingUnratedWhenNoRatingIsProvided(){
        String movieName = "The Social Network";
        String director = "David Fincher";
        int yearOfRelease = 2010;
        Movie aMovie = movie(movieName, director, yearOfRelease);

        assertEquals("unrated", aMovie.rating());
    }

}