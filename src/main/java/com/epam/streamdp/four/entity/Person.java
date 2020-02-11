package com.epam.streamdp.four.entity;

public class Person extends University {
    private int personId;
    private String firstName;
    private String lastName;
    private String city;

    public Person(int personId, String firstName, String lastName, String city) {
        super();
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Person(String universityName, int personId, String firstName, String lastName, String city) {
        super(universityName);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Person(Person person) {
        this.personId = person.getPersonId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.city = person.getCity();
    }

    public Person() {

    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

}
