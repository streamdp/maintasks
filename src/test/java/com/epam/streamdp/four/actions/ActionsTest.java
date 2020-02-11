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

public class ActionsTest {
    List<StatementOfGrades> statementOfGrades = SaveReadItems.loadTestItemsFromFile("students_data" + File.separator + "itemForTests.dat");
    Actions testableAction = new Actions(statementOfGrades);

    @Test(description = "Verify average mark calculated for a subject, group, and a faculty")
    public void testCalculateGPAForSpecificSubjectGroupAndFaculty() throws AcademicSubjectFieldCannotBeEmptyException, TheFacultyFieldMustBeSpecifiedException, TheGroupFieldMustBeSpecifiedException {
        assertEquals(testableAction.calculateGPAForSpecificSubjectGroupAndFaculty(
                AcademicSubjects.TFOEE, 2, Faculties.TF), 7.5, "For test data, the value should be");
    }

    @Test(description = "Verify average mark calculated for a specified subject by university")
    public void testCalculateGPAForAUniversitySubject() throws AcademicSubjectFieldCannotBeEmptyException {
        assertEquals(testableAction.calculateGPAForAUniversitySubject(AcademicSubjects.ENGLISH), 6.2,
                "For test data, the value should be");
    }

    @Test(description = "Verify average mark calculated for a specified student by all subjects")
    public void testCalculateGPAForAllStudentSubjects() {
        assertEquals(statementOfGrades.stream()
                .filter(student -> student.getStudent().getPersonId() == 0)
                .mapToDouble(StatementOfGrades::getGrade)
                .average().getAsDouble(), 6.8, "For test data, the value should be");
    }
}