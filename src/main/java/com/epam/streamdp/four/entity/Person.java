package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;

import java.util.Objects;

public class Person extends UniversityFaculty {
    private int personId;
    private String firstName;
    private String lastName;
    private String city;

    public Person(Faculties faculty, int personId, String firstName, String lastName, String city) {
        super(faculty);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Person(Faculties faculty, Person person) {
        super(faculty);
        this.personId = person.getPersonId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.city = person.getCity();

    }

    public Person(String universityName, Faculties faculty, int personId, String firstName, String lastName, String city) {
        super(universityName, faculty);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Person() {
        super();
    }

    public Person(UniversityFaculty universityFaculty, int personId, String firstName, String lastName, String city) {
        super(universityFaculty.getUniversityName(), universityFaculty.getFaculty());
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

    public Person(UniversityFaculty universityFaculty, Person person) {
        super(universityFaculty);
        this.personId = person.getPersonId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.city = person.getCity();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personId, firstName, lastName, city);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("personId=").append(personId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(lastName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
