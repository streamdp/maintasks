package com.epam.streamdp.lerning.collection;

import com.epam.streamdp.lerning.entity.Person;

import java.util.TreeSet;

public class ComparableMain {
    public static void main(String[] args) {
        TreeSet<Person> set = new TreeSet<>();
        set.add(new Person(701,"Bob"));
        set.add(new Person(509, "Jack"));
        set.add(new Person(827, "Robert"));
        set.add(new Person(109, "Pet"));
        System.out.println(set);
    }
}
