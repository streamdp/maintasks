package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;

import java.util.Objects;

public class University {
    private String universityName = "GSTU";
    private Faculties faculty;

    public University() {
    }

    public University(String universityName, Faculties faculty) {
        this.universityName = universityName;
        this.faculty = faculty;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(universityName, that.universityName) &&
                faculty == that.faculty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName, faculty);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("University{");
        sb.append("universityName='").append(universityName).append('\'');
        sb.append(", faculty=").append(faculty);
        sb.append('}');
        return sb.toString();
    }
}
