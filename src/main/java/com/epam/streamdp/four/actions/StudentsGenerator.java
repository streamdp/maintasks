package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.TheGroupFieldMustBeSpecifiedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsGenerator {
    private Random random = new Random();
    private static final String INITIAL_DIR = "students_data";
    private static final int MINIMAL_YEAR_OF_STUDY = 2015;
    private static final int RANDOM_YEAR = 5;
    private static final int GROUP_COUNT = 2;
    private static final String UNIVERSITY_NAME = "GSTU";
    private static final int GRADE_RANGE = 10;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentsGenerator.class.getName());

    public List<Student> generateSomeStudents(int countStudents) throws TheGroupFieldMustBeSpecifiedException {
        List<Student> students = new ArrayList<>(countStudents);
        List<String> cities = SaveReadItems.loadItemsFromFile(INITIAL_DIR + File.separator + "city");
        List<String> firstNames = SaveReadItems.loadItemsFromFile(INITIAL_DIR + File.separator + "fistNames");
        List<String> lastNames = SaveReadItems.loadItemsFromFile(INITIAL_DIR + File.separator + "lastNames");

        for (int i = 0; i < countStudents; i++) {
            Faculties someFaculty = Faculties.values()[random.nextInt(Faculties.values().length)];
            int someGroup = random.nextInt(GROUP_COUNT) + 1;
            String someFirstName = firstNames.get(random.nextInt(firstNames.size()));
            String someLastName = lastNames.get(random.nextInt(lastNames.size()));
            String someCity = cities.get(random.nextInt(cities.size()));
            int someYearOfStudy = MINIMAL_YEAR_OF_STUDY + random.nextInt(RANDOM_YEAR);

            students.add(new Student(UNIVERSITY_NAME, someFaculty, someGroup, i, someFirstName, someLastName,
                    someCity, someYearOfStudy));
        }
        return students;
    }

    public List<StatementOfGrades> generateSomeSubjectPerStudent(List<Student> students, int countSubjectsPerStudent) {
        List<StatementOfGrades> statementOfGrades = new ArrayList<>();

        students.forEach(student -> {
            for (int i = 0; i < countSubjectsPerStudent; i++) {
                AcademicSubjects someAcademicSubject = AcademicSubjects.values()[random.nextInt(AcademicSubjects.values().length)];
                int someGrade = random.nextInt(GRADE_RANGE);
                statementOfGrades.add(new StatementOfGrades(student,
                        someAcademicSubject, someGrade));
            }
        });

        return statementOfGrades;
    }
}
