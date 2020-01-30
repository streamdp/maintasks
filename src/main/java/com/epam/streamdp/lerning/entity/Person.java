package com.epam.streamdp.lerning.entity;

import java.util.Comparator;

public class Person implements Comparable<Person>{
    private int personId;
    private String name = "";
    public Person(){
    }

    public Person(int id) {
        super();
        this.personId = id;
    }
    public  class NameComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public Person(String name){
        this.name = name;
    }

    public Person(int id, String name){
        this.personId = id;
        this.name = name;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("personId=").append(personId);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Person o) {
//        int result = personId - o.personId;
//        int value = 0;
//
//        if (result > 0) {
//            value = 1;
//        } else if (result < 0) {
//            value = -1;
//        }
//        return value;
        return -name.compareTo(o.name);
    }
}
