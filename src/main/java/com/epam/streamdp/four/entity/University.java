package com.epam.streamdp.four.entity;

import com.epam.streamdp.four.enums.Faculties;
import com.epam.streamdp.four.exception.FieldUniversityNameMustBeSpecifyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class University implements Serializable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(com.epam.streamdp.four.entity.University.class.getName());
    private List<Faculties> faculties = new ArrayList<>();
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

    public List<Faculties> getFaculties() {
        return faculties;
    }

    public University() {
    }

    public String getUniversityName() {
        return universityName;
    }

}
