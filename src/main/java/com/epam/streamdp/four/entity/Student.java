package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;

public class Student extends Person {
    private Faculties faculty;
    private int group;
    private int yearOfStudy;

    public Student(Faculties universityFaculty, Person person) {
        super(person);
        this.faculty = universityFaculty;
    }

    public Student(String universityName, Faculties faculty, int group, int personId, String firstName, String lastName,
                   String city, int yearOfStudy) throws TheGroupFieldMustBeSpecifiedException {
        super(universityName, personId, firstName, lastName, city);
        this.faculty = faculty;
        this.group = isGroupCorrect(group);
        this.yearOfStudy = yearOfStudy;
    }

    public Student(Faculties faculty, Person person, int group, int yearOfStudy) throws TheGroupFieldMustBeSpecifiedException {
        super(person);
        this.faculty = faculty;
        this.group = isGroupCorrect(group);
        this.yearOfStudy = yearOfStudy;

    }

    public Student(Person person, Faculties faculty, int group, int yearOfStudy) {
        super(person);
        this.faculty = faculty;
        this.group = group;
        this.yearOfStudy = yearOfStudy;
    }

    public Student(Student student) {
        super();
        this.faculty = student.getFaculty();
        this.group = student.getGroup();
        this.yearOfStudy = student.yearOfStudy;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    private int isGroupCorrect(int group) throws TheGroupFieldMustBeSpecifiedException {
        if (group > 0) {
            return group;
        } else {
            throw new TheGroupFieldMustBeSpecifiedException("Group field must be specify. Correct value must be > 0!");
        }
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }

    public int getGroup() {
        return group;
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