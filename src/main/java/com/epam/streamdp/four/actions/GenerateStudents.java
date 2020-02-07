package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;
import com.epam.streamdp.four.entity.Student;
import com.epam.streamdp.four.enums.AcademicSubjects;
import com.epam.streamdp.four.enums.Faculties;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateStudents {
    private Random random = new Random();

    public List<Student> generateSomeStudents(int countStudents) {
        List<Student> students = new ArrayList<>(countStudents);
        List<NameFromJson> cities = SaveReadItemsFromJson.loadItemsFromFile("students_data/city.json");
        List<NameFromJson> firstNames = SaveReadItemsFromJson.loadItemsFromFile("students_data/fistNames.json");
        List<NameFromJson> lastNames = SaveReadItemsFromJson.loadItemsFromFile("students_data/lastNames.json");

        for (int i = 0; i < countStudents; i++) {
            students.add(new Student("GSTU",
                    Faculties.values()[random.nextInt(Faculties.values().length)],
                    random.nextInt(2) + 1,
                    i, firstNames.get(random.nextInt(firstNames.size())).getName(),
                    lastNames.get(random.nextInt(lastNames.size())).getName(),
                    cities.get(random.nextInt(cities.size())).getName(),
                    2015 + random.nextInt(5)));
        }
        return students;
    }

    public List<StatementOfGrades> generateSomeSubjectPerStudent(List<Student> students, int countSubjectsPerStudent) {
        List<StatementOfGrades> statementOfGrades = new ArrayList<>();

        students.forEach(student -> {
            for (int i = 0; i < countSubjectsPerStudent; i++) {
                statementOfGrades.add(new StatementOfGrades(student,
                        AcademicSubjects.values()[random.nextInt(AcademicSubjects.values().length)],
                        random.nextInt(10)));
            }
        });

        return statementOfGrades;
    }
}
