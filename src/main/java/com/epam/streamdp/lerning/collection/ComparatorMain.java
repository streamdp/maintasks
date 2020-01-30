package com.epam.streamdp.lerning.collection;

import com.epam.streamdp.lerning.entity.Person;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorMain {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(701,"Bob"));
        list.add(new Person(509, "Jack"));
        list.add(new Person(324, "Jack"));
        list.add(new Person(265, "Jack"));
        list.add(new Person(175, "Jack"));
        list.add(new Person(897, "Jack"));
        list.add(new Person(827, "Robert"));
        list.add(new Person(109, "Pet"));
        System.out.println(list);
        //list.sort(new Person.NameComparator());
        //list.sort(((o1, o2) -> o1.getPersonId()-o2.getPersonId()));
        //Collections.sort(list, new PersonNameComparator());
        //list.sort(new PersonNameComparator());
        list.sort(Comparator.comparing(Person::getName).thenComparing(Person::getPersonId));
        System.out.println(list);
    }
}
