package com.epam.streamdp.lerning.collection;

import com.epam.streamdp.lerning.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class WildSuperMain {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.sort((o1,o2) ->o1.getName().compareTo(o2.getName()));
        action(students);

//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person());
//        action(persons);
    }
    static void action(List<? super Student> list) {
        list.remove(0);
        list.add(new Student());
        //list.add(new Person());
    }

}
