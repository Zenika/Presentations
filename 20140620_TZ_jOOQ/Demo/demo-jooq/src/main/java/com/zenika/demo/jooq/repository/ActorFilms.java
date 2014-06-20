/*
 * 
 */

package com.zenika.demo.jooq.repository;

/**
 *
 */
public class ActorFilms {
    private String firstName;
    private String lastName;
    private int filmNumber;
    private String filmTitles;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFilmNumber() {
        return filmNumber;
    }

    public void setFilmNumber(int filmNumber) {
        this.filmNumber = filmNumber;
    }

    public String getFilmTitles() {
        return filmTitles;
    }

    public void setFilmTitles(String filmTitles) {
        this.filmTitles = filmTitles;
    }
    
}
