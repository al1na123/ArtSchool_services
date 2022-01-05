package com.artschool.servicefeedback.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public final class Feedback{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long feedback_id;
    private Integer rating;
    private String text;
    private long user_id;
    private long course_id;

    public Feedback(){
    }

    public Feedback(Integer rating, String text, long user_id, long course_id){
        this.rating = rating;
        this.text = text;
        this.user_id = user_id;
        this.course_id = course_id;
    }

    public long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }
}