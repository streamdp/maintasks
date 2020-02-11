package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.AcademicSubjectFieldCannotBeEmptyException;
import com.epam.streamdp.four.exception.TheFacultyFieldMustBeSpecifiedException;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActionsTest {
    List<StatementOfGrades> statementOfGrades = SaveReadItems.loadTestItemsFromFile("students_data" + File.separator + "itemForTests.dat");
    Actions testableAction = new Actions(statementOfGrades);

    @Test
    public void testCalculateGPAForSpecificSubjectGroupAndFaculty() throws AcademicSubjectFieldCannotBeEmptyException, TheFacultyFieldMustBeSpecifiedException, TheGroupFieldMustBeSpecifiedException {
        assertTrue(statementOfGrades.stream()
                .filter(subject -> subject.getAcademicSubject().equals(AcademicSubjects.TFOEE))
                .filter(subject -> subject.getGroup() == 2)
                .filter(subject -> subject.getStudent().getFaculty().equals(Faculties.TF))
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble() - testableAction.calculateGPAForSpecificSubjectGroupAndFaculty(
                AcademicSubjects.TFOEE, 2, Faculties.TF) < 0.00001, "The value of the average score " +
                "for the subject and the selected group with faculty obtained in the test does not match the value " +
                "obtained from the tested method");
    }

    @Test
    public void testCalculateGPAForAUniversitySubject() throws AcademicSubjectFieldCannotBeEmptyException {
        assertTrue(statementOfGrades.stream()
                        .filter(subject -> subject.getAcademicSubject().equals(AcademicSubjects.ENGLISH))
                        .mapToDouble(StatementOfGrades::getGrade)
                        .average().getAsDouble() - testableAction.calculateGPAForAUniversitySubject(AcademicSubjects.ENGLISH) < 0.00001,
                "The value of the average grade for the university subject obtained in the test does not " +
                        "coincide with the value obtained from the tested method");
    }

    @Test
    public void testCalculateGPAForAllStudentSubjects() {
        assertEquals(statementOfGrades.stream()
                .filter(student -> student.getStudent().getPersonId() == 0)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble(), 6.8, "For test data, the value should be");
    }
}