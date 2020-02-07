package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;

import java.util.List;
import java.util.OptionalDouble;

public class Actions {
    private List<StatementOfGrades> statementOfGrades;

    public Actions(List<StatementOfGrades> statementOfGrades) {
        this.statementOfGrades = statementOfGrades;
    }


    public OptionalDouble calculateGPAForSpecificSubjectGroupAndFaculty(AcademicSubjects academicSubjects, int group, Faculties faculties) {
        return statementOfGrades
                .stream()
                .filter(subject -> subject.getAcademicSubject() == academicSubjects && subject.getStudent().getGroup() == group
                        && subject.getStudent().getFaculty() == faculties)
                .mapToDouble(StatementOfGrades::getScore)
                .average();
    }


    public OptionalDouble сalculateGPAForAUniversitySubject(AcademicSubjects academicSubjects) {
        return statementOfGrades
                .stream()
                .filter(academicSubject -> academicSubject.getAcademicSubject() == academicSubjects)
                .mapToDouble(StatementOfGrades::getScore)
                .average();
    }


    public OptionalDouble calculateGPAForAllStudentSubjects(int personId) {
        return statementOfGrades
                .stream()
                .filter(statementOfGrades -> statementOfGrades.getStudent().getPersonId() == personId)
                .mapToDouble(StatementOfGrades::getScore)
                .average();
    }
}
