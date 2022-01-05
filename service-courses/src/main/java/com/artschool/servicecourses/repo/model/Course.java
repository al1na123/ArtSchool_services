package com.artschool.servicecourses.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public final class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long course_id;
    private String name;
    private Integer price;
    private String future_job;

    public Course() {
    }

    public Course(String name, Integer price, String future_job) {
        this.name = name;
        this.price = price;
        this.future_job = future_job;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFuture_job() {
        return future_job;
    }

    public void setFuture_job(String future_job) {
        this.future_job = future_job;
    }

}

