package com.epam.streamdp.four.exception;

public class ThereAreNoStudentsInTheGroupException extends Exception {
    public ThereAreNoStudentsInTheGroupException() {
    }

    public ThereAreNoStudentsInTheGroupException(String message) {
        super(message);
    }
}
