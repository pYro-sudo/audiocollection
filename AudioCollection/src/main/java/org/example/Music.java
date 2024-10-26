package org.example;

import java.util.Date;

public class Music {
    private String id;
    private AudioType type;
    private int releaseYear;
    private Subscription availableOnLevel;

    private Genre genre;

    private Review review;

    public Music(String name, AudioType type, int releaseYear, Subscription availableOnLevel, Genre genre, Review review){
        this.id = name;
        this.availableOnLevel = availableOnLevel;
        this.type = type;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.review = review;
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public Subscription changeAvailabilityLevel(Subscription subscription){
        this.availableOnLevel = subscription;
        return subscription;
    }

    public Subscription getAvailableOnLevel(){
        return availableOnLevel;
    }

    public AudioType getType() {
        return this.type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
