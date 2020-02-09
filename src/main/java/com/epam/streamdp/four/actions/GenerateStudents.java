package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.InvalidSubjectGradeException;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class GenerateStudents {
    private Random random = new Random();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(com.epam.streamdp.four.actions.GenerateStudents.class.getName());

    public List<Student> generateSomeStudents(int countStudents) throws TheGroupFieldMustBeSpecifiedException {
        List<Student> students = new ArrayList<>(countStudents);
        List<NameFromJson> cities = SaveReadItemsFromJson.loadItemsFromFile("students_data" + File.separator + "city.json");
        List<NameFromJson> firstNames = SaveReadItemsFromJson.loadItemsFromFile("students_data" + File.separator + "fistNames.json");
        List<NameFromJson> lastNames = SaveReadItemsFromJson.loadItemsFromFile("students_data" + File.separator + "lastNames.json");

        for (int i = 0; i < countStudents; i++) {
            String universityName = "GSTU";
            Faculties someFaculty = Faculties.values()[random.nextInt(Faculties.values().length)];
            int someGroup = random.nextInt(2) + 1;
            String someFirstName = firstNames.get(random.nextInt(firstNames.size())).getName();
            String someLastName = lastNames.get(random.nextInt(lastNames.size())).getName();
            String someCity = cities.get(random.nextInt(cities.size())).getName();
            int someYearOfStudy = 2015 + random.nextInt(5);

            students.add(new Student(universityName, someFaculty, someGroup, i, someFirstName, someLastName,
                    someCity, someYearOfStudy));
        }
        return students;
    }

    public List<StatementOfGrades> generateSomeSubjectPerStudent(List<Student> students, int countSubjectsPerStudent) {
        List<StatementOfGrades> statementOfGrades = new ArrayList<>();

        students.forEach(student -> {
            for (int i = 0; i < countSubjectsPerStudent; i++) {
                try {
                    AcademicSubjects someAcademicSubject = AcademicSubjects.values()[random.nextInt(AcademicSubjects.values().length)];
                    int someGrade = random.nextInt(10);
                    statementOfGrades.add(new StatementOfGrades(student,
                            someAcademicSubject, someGrade));
                } catch (InvalidSubjectGradeException ex) {
                    logger.log(Level.SEVERE, "Exception: ", ex);
                }
            }
        });

        return statementOfGrades;
    }
}
