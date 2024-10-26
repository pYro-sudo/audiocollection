package org.example;

import java.util.Date;

public class Review {
    private String PersonWhoReviewed;

    private String NameOfTheMusicReviewed;
    private String review;
    private Date dateOfReview;
    private double rating;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(Date dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getNameOfTheMusicReviewed() {
        return NameOfTheMusicReviewed;
    }

    public void setNameOfTheMusicReviewed(String nameOfTheMusicReviewed) {
        NameOfTheMusicReviewed = nameOfTheMusicReviewed;
    }

    public String getPersonWhoReviewed() {
        return PersonWhoReviewed;
    }

    public void setPersonWhoReviewed(String personWhoReviewed) {
        PersonWhoReviewed = personWhoReviewed;
    }
}
