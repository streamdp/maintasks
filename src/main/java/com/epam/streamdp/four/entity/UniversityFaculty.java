package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;

import java.util.Objects;

public class UniversityFaculty extends University {
    private Faculties faculty;

    public UniversityFaculty(University university, Faculties faculty) {
        super(university);
        this.faculty = faculty;
    }

    public UniversityFaculty(String universityName, Faculties faculty) {
        super(universityName);
        this.faculty = faculty;
    }

    public UniversityFaculty(Faculties faculty) {
        super();
        this.faculty = faculty;
    }

    public UniversityFaculty() {

    }

    public UniversityFaculty(UniversityFaculty universityFaculty) {
        super(universityFaculty.getUniversityName());
        this.faculty = universityFaculty.getFaculty();
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
        if (!super.equals(o)) return false;
        UniversityFaculty that = (UniversityFaculty) o;
        return faculty == that.faculty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("faculty=").append(faculty);
        sb.append('}');
        return sb.toString();
    }
}
