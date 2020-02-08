package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class GenerateStudentsTest {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GenerateStudentsTest.class.getName());
    List<Student> students;

    {
        try {
            students = new GenerateStudents().generateSomeStudents(20);
        } catch (TheGroupFieldMustBeSpecifiedException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    @Test
    public void testGenerateSomeStudents() throws TheGroupFieldMustBeSpecifiedException {
        assertEquals(students.stream()
                .filter(student -> !student.getFirstName().isEmpty())
                .filter(student -> !student.getLastName().isEmpty())
                .filter(student -> student.getPersonId() >= 0)
                .filter(student -> student.getFaculty() != null)
                .filter(student -> student.getGroup() > 0)
                .collect(Collectors.toList()).size(), 20);
    }

    @Test
    public void testGenerateSomeSubjectPerStudent() {
        assertEquals(new GenerateStudents().generateSomeSubjectPerStudent(students, 5).stream()
                .filter(subject -> subject.getAcademicSubject() != null)
                .filter(subject -> subject.getGrade() >= 0 && subject.getGrade() <= 10)
                .collect(Collectors.toList()).size(), 100);
    }
}