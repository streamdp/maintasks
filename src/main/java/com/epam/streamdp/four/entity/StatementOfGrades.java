package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.exception.InvalidSubjectGradeException;

import java.util.Objects;
import java.util.logging.Level;

public class StatementOfGrades extends Student {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(com.epam.streamdp.four.entity.StatementOfGrades.class.getName());

    private Student student;
    private AcademicSubjects academicSubject;
    private int grade;

    public StatementOfGrades(Student student, AcademicSubjects academicSubject, int grade) throws InvalidSubjectGradeException {
        super(student);
        this.student = student;
        this.academicSubject = academicSubject;
        try {
            if (grade >= 0 && grade <= 10) {
                this.grade = grade;
            } else {
                throw new InvalidSubjectGradeException("Please verify input value of grade. The value must be in the range [0..10]!");
            }
        } catch (InvalidSubjectGradeException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public AcademicSubjects getAcademicSubject() {
        return academicSubject;
    }

    public Student getStudent() {
        return student;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatementOfGrades that = (StatementOfGrades) o;
        return grade == that.grade &&
                Objects.equals(student, that.student) &&
                academicSubject == that.academicSubject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), student, academicSubject, grade);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nStatementOfGrades{");
        sb.append(student);
        sb.append(", academicSubject=").append(academicSubject);
        sb.append(", score=").append(grade);
        sb.append('}');
        return sb.toString();
    }
}
