package net.laggedhero.gyg.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmReview extends RealmObject {

  @PrimaryKey private long reviewId;
  private String rating;
  private String title;
  private String message;
  private String author;
  private boolean foreignLanguage;
  private String date;
  private String languageCode;
  private String reviewerName;
  private String reviewerCountry;
  private String travelerType;

  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public boolean isForeignLanguage() {
    return foreignLanguage;
  }

  public void setForeignLanguage(boolean foreignLanguage) {
    this.foreignLanguage = foreignLanguage;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public String getReviewerCountry() {
    return reviewerCountry;
  }

  public void setReviewerCountry(String reviewerCountry) {
    this.reviewerCountry = reviewerCountry;
  }

  public String getTravelerType() {
    return travelerType;
  }

  public void setTravelerType(String travelerType) {
    this.travelerType = travelerType;
  }
}
