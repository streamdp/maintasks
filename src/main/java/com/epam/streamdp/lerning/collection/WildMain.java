package com.epam.streamdp.lerning.collection;

import com.epam.streamdp.lerning.entity.Person;
import com.epam.streamdp.lerning.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WildMain {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person());
        list.add(new Student());
        action(list);
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        action(students);
    }
    static void action(List<? extends Person> list){
        list.remove(0);
        //list.add(new Person());
        Stream<? extends Person> stream = list.stream();
    }
}
