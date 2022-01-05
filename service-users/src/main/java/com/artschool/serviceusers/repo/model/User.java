package com.artschool.serviceusers.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String first_name;
    private String last_name;
    private Integer age;
    private long course_id;

    public User(){
    }

    public User(String first_name, String last_name, Integer age, long course_id){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.course_id = course_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }
}
