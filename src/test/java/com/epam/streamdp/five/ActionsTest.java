package com.epam.streamdp.five;

import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class ActionsTest {
    public static final String METHOD_ERROR_MESSAGE = "The method does not work correctly";
    public static final Path TEST_DATA_PATH = Paths.get("data/fileTreeTest");
    private static final int NUMBER_OF_DIRECTORIES = 0;
    private static final int NUMBER_OF_FILES = 1;
    private static final int AVERAGE_NUMBER_FILES = 2;
    private static final int AVERAGE_LENGTH_FILE_NAMES = 3;
    
    @Test(description = "Verify how to create a separator for the file name.")
    public void testMakeTabs() {
        assertEquals(new Actions().makeTabs(5), "|\t\t\t\t\t", METHOD_ERROR_MESSAGE);
    }

    @Test(description = "Verify how to create a separator for the directory name.")
    public void testMakeDirSeparators() {
        assertEquals(new Actions().makeDirSeparators(5), "--\t--\t--\t--\t--\t", METHOD_ERROR_MESSAGE);
    }

    @Test(description = "Verify how file name string clipping works.")
    public void testGetFileNameFromString() {
        assertEquals(new Actions().getFileNameFromString("|\t\t\t\t_String filename.txt"),
                "_String filename.txt", METHOD_ERROR_MESSAGE);
        assertEquals(new Actions().getFileNameFromString("_"), "_", METHOD_ERROR_MESSAGE);
    }

    @Test(description = "Verify if the resulting string is a directory name.")
    public void testIsStringContainedDirectory() {
        assertTrue(new Actions().isStringContainedDirectory("|--\t--\t--\tdirectoryName"), METHOD_ERROR_MESSAGE);
        assertFalse(new Actions().isStringContainedDirectory("|\tsomeFileName"), METHOD_ERROR_MESSAGE);
    }

    @Test(description = "Verify if the resulting string is a file name.")
    public void testIsStringContainedFiles() {
        assertTrue(new Actions().isStringContainedFiles("|\tsomeFileName"), METHOD_ERROR_MESSAGE);
        assertFalse(new Actions().isStringContainedFiles("|--\t--\t--\tdirectoryName"), METHOD_ERROR_MESSAGE);
    }

    @Test(description = "Verify method receiving answers to the second part of the task.")
    public void testGetAnswersForPartTwoMainTask() {
        int[] arrayWithAnswers = new Actions().getAnswersForPartTwoMainTask(TEST_DATA_PATH);
        assertEquals(arrayWithAnswers.length, 4, METHOD_ERROR_MESSAGE);
        assertEquals(arrayWithAnswers[NUMBER_OF_DIRECTORIES], 5, METHOD_ERROR_MESSAGE);
        assertEquals(arrayWithAnswers[NUMBER_OF_FILES], 26, METHOD_ERROR_MESSAGE);
        assertEquals(arrayWithAnswers[AVERAGE_NUMBER_FILES], 5, METHOD_ERROR_MESSAGE);
        assertEquals(arrayWithAnswers[AVERAGE_LENGTH_FILE_NAMES], 12, METHOD_ERROR_MESSAGE);
    }
}