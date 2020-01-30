package com.epam.streamdp.lerning.collection;

import com.epam.streamdp.lerning.entity.Person;
import com.epam.streamdp.lerning.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class GenericMain {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add( new Student());
        list.add( new Student());
        list.add( new Student());
        //list.add("");
        list.add( new Person());
        Person person = list.get(2);


    }
}
