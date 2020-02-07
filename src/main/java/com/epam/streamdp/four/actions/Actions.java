package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.AcademicSubjectFieldCannotBeEmptyException;
import com.epam.streamdp.four.exception.TheFacultyFieldMustBeSpecifiedException;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;
import com.epam.streamdp.four.exception.ThereAreNoStudentsInTheGroupException;

import java.util.List;
import java.util.stream.Collectors;

public class Actions {
    private static final String SET_DOES_NOT_EXIST = "The set with the selected parameters does not exist!";
    private List<StatementOfGrades> statementOfGrades;

    public Actions(List<StatementOfGrades> statementOfGrades) {
        this.statementOfGrades = statementOfGrades;
    }


    public double calculateGPAForSpecificSubjectGroupAndFaculty(AcademicSubjects academicSubjects, int group, Faculties faculties)
            throws AcademicSubjectFieldCannotBeEmptyException, TheGroupFieldMustBeSpecifiedException, TheFacultyFieldMustBeSpecifiedException {
        statementOfGrades = statementOfGrades
                .stream()
                .filter(subject -> subject.getAcademicSubject() == academicSubjects)
                .collect(Collectors.toList());
        if (!statementOfGrades.isEmpty()) {
            statementOfGrades = statementOfGrades
                    .stream()
                    .filter(subject -> subject.getStudent().getGroup() == group)
                    .collect(Collectors.toList());
            if (!statementOfGrades.isEmpty()) {
                statementOfGrades = statementOfGrades
                        .stream()
                        .filter(subject -> subject.getStudent().getFaculty() == faculties)
                        .collect(Collectors.toList());
                if (!statementOfGrades.isEmpty()) {
                    return statementOfGrades
                            .stream()
                            .mapToDouble(StatementOfGrades::getGrade)
                            .average().getAsDouble();
                } else {
                    throw new TheFacultyFieldMustBeSpecifiedException(SET_DOES_NOT_EXIST);
                }
            } else {
                throw new TheGroupFieldMustBeSpecifiedException(SET_DOES_NOT_EXIST);
            }
        } else {
            throw new AcademicSubjectFieldCannotBeEmptyException(SET_DOES_NOT_EXIST);
        }
    }

    public double calculateGPAForAUniversitySubject(AcademicSubjects academicSubjects) throws AcademicSubjectFieldCannotBeEmptyException {
        return statementOfGrades
                .stream()
                .filter(academicSubject -> academicSubject.getAcademicSubject() == academicSubjects)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().orElseThrow(() -> new AcademicSubjectFieldCannotBeEmptyException(SET_DOES_NOT_EXIST));
    }


    public double calculateGPAForAllStudentSubjects(int personId) throws ThereAreNoStudentsInTheGroupException {
        return statementOfGrades
                .stream()
                .filter(student -> student.getStudent().getPersonId() == personId)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().orElseThrow(() -> new ThereAreNoStudentsInTheGroupException(SET_DOES_NOT_EXIST));
    }
}
