package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;

import java.util.Objects;

public class Student extends Person {
    private Faculties faculty;
    private int group;
    private int yearOfStudy;


    public Student(String universityName, Faculties faculty, int group, int personId, String firstName, String lastName,
                   String city, int yearOfStudy) {
        super(universityName, faculty, personId, firstName, lastName, city);
        this.group = group;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
    }

    public Student() {

    }

    public int getGroup() {
        return group;
    }

    @Override
    public Faculties getFaculty() {
        return faculty;
    }

    @Override
    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return yearOfStudy == student.yearOfStudy &&
                faculty == student.faculty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty, yearOfStudy);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("faculty=").append(faculty);
        sb.append(", group=").append(group);
        sb.append(", firstName=").append(getFirstName());
        sb.append(", lastName=").append(getLastName());
        sb.append(", yearOfStudy=").append(yearOfStudy);
        sb.append('}');
        return sb.toString();
    }
}