package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.assertEquals;

public class StudentsGeneratorTest {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentsGeneratorTest.class.getName());
    private List<Student> students = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        try {
            students = new StudentsGenerator().generateSomeStudents(20);
        } catch (TheGroupFieldMustBeSpecifiedException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    @Test(description = "Verify that the method generates the correct set. There should be " +
            "no empty fields in the set, field person id must be a positive, field group cannot be negative or zero")
    public void testGenerateSomeStudents() {
        assertEquals((int) students.stream()
                .filter(student -> !student.getFirstName().isEmpty())
                .filter(student -> !student.getLastName().isEmpty())
                .filter(student -> student.getPersonId() >= 0)
                .filter(student -> student.getFaculty() != null)
                .filter(student -> student.getGroup() > 0).count(), 20, "There are errors as a " +
                "result of the method. Incorrect fields in the set.");
    }

    @Test(description = "Verify that the method generates the correct set. There should be " +
            "no empty fields in the set and field grade must be in range [0..10]")
    public void testGenerateSomeSubjectPerStudent() {
        assertEquals((int) new StudentsGenerator().generateSomeSubjectPerStudent(students, 5).stream()
                        .filter(subject -> subject.getAcademicSubject() != null)
                        .filter(subject -> subject.getGrade() >= 0 && subject.getGrade() <= 10).count(), 100,
                "Fields AcademicSubject must not be empty. The value of grade must be in the range [0..10]!");
    }
}