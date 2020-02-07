package com.epam.streamdp.four.maintask;

import com.epam.streamdp.four.actions.Actions;
import com.epam.streamdp.four.actions.GenerateStudents;
import com.epam.streamdp.four.entity.*;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.*;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class MainTask {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTask.class.getName());
    private static final String CASE_GENERATE_EXCEPTION = "This case generate exception {0}:";
    private static final String EXCEPTION_SEVERE_LEVEL_MESSAGE = "Exception: ";

    public static void main(String[] args) throws
            InvalidSubjectGradeException,
            AcademicSubjectFieldCannotBeEmptyException,
            ThereAreNoStudentsInTheGroupException,
            TheGroupFieldMustBeSpecifiedException {

        loadAndApplyLoggingConfig();
        List<Student> students = new GenerateStudents().generateSomeStudents(10);
        List<StatementOfGrades> statementOfGrades = new GenerateStudents().generateSomeSubjectPerStudent(students, 5);
        logger.log(Level.INFO, "This case generate some student and subject with grade per student: {0}", statementOfGrades);

        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "InvalidSubjectGradeException");
        statementOfGrades.add(new StatementOfGrades(statementOfGrades.get(0).getStudent(),
                AcademicSubjects.COMBUSTION_THEORY, -1));
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "FieldUniversityNameMustBeSpecifyException");
        University university = new University("");
        university.getUniversityName();
        UniversityFaculty universityFaculty = new UniversityFaculty(Faculties.PEF);
        Person person = new Person(universityFaculty, 200, "Ivan", "Ivanov", "Zhlobin");
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "TheGroupFieldMustBeSpecifiedException");
        try {
            students.add(new Student(universityFaculty, person, 0, 2011));
        } catch (TheGroupFieldMustBeSpecifiedException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "AcademicSubjectFieldCannotBeEmptyException");
        try {
            if (statementOfGrades
                    .stream()
                    .filter(student -> student.getPersonId() == 200)
                    .filter(student -> student.getAcademicSubject() != null)
                    .collect(Collectors.toList()).isEmpty()) {
                throw new AcademicSubjectFieldCannotBeEmptyException("Student must have at least one subject!");
            }
        } catch (AcademicSubjectFieldCannotBeEmptyException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "ThereAreNoStudentsInTheGroupException");
        try {
            if (statementOfGrades
                    .stream()
                    .filter(group -> group.getGroup() == 3)
                    .collect(Collectors.toList()).isEmpty()) {
                throw new ThereAreNoStudentsInTheGroupException("Group must contain at least one student!");
            }
        } catch (ThereAreNoStudentsInTheGroupException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "TheFacultyMustContainAtLeastOneGroup");
        try {
            Faculties mefPef = null;
            students.add(new Student(universityFaculty, person));
            students.stream()
                    .filter(student -> student.getPersonId() == 200)
                    .collect(Collectors.toList()).get(0).setFaculty(mefPef);
            if (statementOfGrades
                    .stream()
                    .filter(faculty -> faculty.getFaculty() == mefPef)
                    .filter(group -> group.getGroup() > 0)
                    .collect(Collectors.toList()).isEmpty()) {
                throw new TheFacultyMustContainAtLeastOneGroup("Faculty must contain at least one group!");
            }
        } catch (TheFacultyMustContainAtLeastOneGroup ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        logger.log(Level.INFO, CASE_GENERATE_EXCEPTION, "TheUniversityMustContainAtLeastOneFaculty");
        try {
            UniversityFaculty universityFacultyTest = new UniversityFaculty("TEST_UNIVERCITY", null);
            if (universityFacultyTest.getFaculty() == null) {
                throw new TheUniversityMustContainAtLeastOneFaculty("University must contain at least one faculty!");
            }
        } catch (TheUniversityMustContainAtLeastOneFaculty ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        logger.log(Level.INFO, "GPA for all student with personId = 8 subjects = {0}",
                new Actions(statementOfGrades).calculateGPAForAllStudentSubjects(8));
        logger.log(Level.INFO, "GPA for a university subject COMBUSTION_THEORY = {0}",
                new Actions(statementOfGrades).calculateGPAForAUniversitySubject(AcademicSubjects.COMBUSTION_THEORY));

        try {
            logger.log(Level.INFO, "GPA for specific subject group and faculty with some chance of throwing an exception = {0}",
                    new Actions(statementOfGrades).calculateGPAForSpecificSubjectGroupAndFaculty(AcademicSubjects.ECONOMY, 2, Faculties.AAISF));
        } catch (AcademicSubjectFieldCannotBeEmptyException | TheGroupFieldMustBeSpecifiedException | TheFacultyFieldMustBeSpecifiedException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
    }
}
