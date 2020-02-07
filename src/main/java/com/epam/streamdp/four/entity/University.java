package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.exception.FieldUniversityNameMustBeSpecifyException;

import java.util.Objects;
import java.util.logging.Level;

public class University {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(com.epam.streamdp.four.entity.University.class.getName());

    private String universityName = "GSTU";

    public University(University university) {
    }

    public University(String universityName) {
        try {
            if (!universityName.isEmpty()) {
                this.universityName = universityName;
            } else {
                throw new FieldUniversityNameMustBeSpecifyException("You must specify University name!");
            }
        } catch (FieldUniversityNameMustBeSpecifyException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public University() {
    }

    public String getUniversityName() {
        return universityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(universityName, that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("University{");
        sb.append("universityName='").append(universityName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
