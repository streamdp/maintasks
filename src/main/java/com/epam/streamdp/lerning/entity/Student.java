package com.epam.streamdp.lerning.entity;

public class Student extends Person {
    private String faculty;
    private int yearOfStudy;

    public Student() {
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getFaculty() {
        return faculty;
    }
}