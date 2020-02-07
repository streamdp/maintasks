package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;

import java.util.Objects;

public class StatementOfGrades extends Student {
    private Student student;
    private AcademicSubjects academicSubject;
    private int score;

    public StatementOfGrades(String universityName, Faculties faculty, int group, int personId, String firstName, String lastName, String city, int yearOfStudy) {
        super(universityName, faculty, group, personId, firstName, lastName, city, yearOfStudy);
    }

    public StatementOfGrades(Student student, AcademicSubjects academicSubject, int score) {
        super();
        this.student = student;
        this.academicSubject = academicSubject;
        this.score = score;
    }

    public AcademicSubjects getAcademicSubject() {
        return academicSubject;
    }

    public Student getStudent() {
        return student;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatementOfGrades that = (StatementOfGrades) o;
        return score == that.score &&
                Objects.equals(student, that.student) &&
                academicSubject == that.academicSubject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), student, academicSubject, score);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nStatementOfGrades{");
        sb.append(student);
        sb.append(", academicSubject=").append(academicSubject);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
