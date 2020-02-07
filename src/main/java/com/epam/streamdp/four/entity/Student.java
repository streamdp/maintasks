package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;

import java.util.Objects;

public class Student extends Person {
    private int group;
    private int yearOfStudy;

    public Student(UniversityFaculty universityFaculty, Person person) {
        super(universityFaculty, person);
    }

    public Student(String universityName, Faculties faculty, int group, int personId, String firstName, String lastName,
                   String city, int yearOfStudy) throws TheGroupFieldMustBeSpecifiedException {
        super(universityName, faculty, personId, firstName, lastName, city);
        this.group = isGroupCorrect(group);
        this.yearOfStudy = yearOfStudy;
    }

    public Student(Faculties faculty, Person person, int group, int yearOfStudy) throws TheGroupFieldMustBeSpecifiedException {
        super(faculty, person);
        this.group = isGroupCorrect(group);
        this.yearOfStudy = yearOfStudy;

    }

    public Student(UniversityFaculty faculty, Person person, int group, int yearOfStudy) throws TheGroupFieldMustBeSpecifiedException {
        super(faculty.getFaculty(), person);
        this.group = isGroupCorrect(group);
        this.yearOfStudy = yearOfStudy;

    }

    public Student(Student student) {
        super();
        this.group = student.getGroup();
        this.yearOfStudy = getYearOfStudy();
    }

    public Student(Person person, int group, int yearOfStudy) {
        super(person);
        this.group = group;
        this.yearOfStudy = yearOfStudy;
    }

    private int isGroupCorrect(int group) throws TheGroupFieldMustBeSpecifiedException {
        if (group > 0) {
            return group;
        } else {
            throw new TheGroupFieldMustBeSpecifiedException("Group field must be specify. Correct value must be > 0!");
        }
    }

    public int getGroup() {
        return group;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return group == student.group &&
                yearOfStudy == student.yearOfStudy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, yearOfStudy);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("faculty=").append(super.getFaculty());
        sb.append(", group=").append(group);
        sb.append(", firstName=").append(getFirstName());
        sb.append(", lastName=").append(getLastName());
        sb.append(", yearOfStudy=").append(yearOfStudy);
        sb.append('}');
        return sb.toString();
    }
}