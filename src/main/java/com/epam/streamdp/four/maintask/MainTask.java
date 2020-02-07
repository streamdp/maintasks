package com.epam.streamdp.four.maintask;

import com.epam.streamdp.four.actions.Actions;
import com.epam.streamdp.four.actions.GenerateStudents;
import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;

import java.util.List;

public class MainTask {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTask.class.getName());

    public static void main(String[] args) {
        List<Student> students = GenerateStudents.generateSomeStudents(10);
        List<StatementOfGrades> statementOfGrades = GenerateStudents.generateSomeSubjectPerStudent(students, 5);
        System.out.println(students);
        System.out.println(statementOfGrades);
        System.out.println(new Actions(statementOfGrades).calculateGPAForAllStudentSubjects(8).getAsDouble());
        System.out.println(new Actions(statementOfGrades).сalculateGPAForAUniversitySubject(AcademicSubjects.COMBUSTION_THEORY).getAsDouble());
        System.out.println(new Actions(statementOfGrades).calculateGPAForSpecificSubjectGroupAndFaculty(AcademicSubjects.ECONOMY, 2, Faculties.AAISF).getAsDouble());
    }
}
