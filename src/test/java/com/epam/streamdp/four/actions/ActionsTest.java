package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.AcademicSubjectFieldCannotBeEmptyException;
import com.epam.streamdp.four.exception.TheFacultyFieldMustBeSpecifiedException;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActionsTest {
    List<StatementOfGrades> statementOfGrades = SaveReadItemsFromJson.loadTestItemsFromFile("students_data/itemForTests.json");
    Actions testableAction = new Actions(statementOfGrades);

    @Test
    public void testCalculateGPAForSpecificSubjectGroupAndFaculty() throws AcademicSubjectFieldCannotBeEmptyException, TheFacultyFieldMustBeSpecifiedException, TheGroupFieldMustBeSpecifiedException {
        assertTrue(statementOfGrades.stream()
                .filter(subject -> subject.getAcademicSubject() == AcademicSubjects.ENGLISH)
                .filter(subject -> subject.getGroup() == 2)
                .filter(subject -> subject.getStudent().getFaculty() == Faculties.TF)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble() - testableAction.calculateGPAForSpecificSubjectGroupAndFaculty(
                AcademicSubjects.ENGLISH, 2, Faculties.TF) < 0.00001);
    }


    @Test
    public void testCalculateGPAForAUniversitySubject() throws AcademicSubjectFieldCannotBeEmptyException {
        assertTrue(statementOfGrades.stream()
                .filter(subject -> subject.getAcademicSubject() == AcademicSubjects.ENGLISH)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble() - testableAction.calculateGPAForAUniversitySubject(AcademicSubjects.ENGLISH) < 0.00001);
    }

    @Test
    public void testCalculateGPAForAllStudentSubjects() {
        assertEquals(statementOfGrades.stream()
                .filter(student -> student.getStudent().getPersonId() == 0)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble(), 3.2);
    }
}