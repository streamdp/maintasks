package com.epam.streamdp.five;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.epam.streamdp.five.Actions.*;
import static org.testng.Assert.assertEquals;

public class ActionsTest {
    public static final Path TEST_DATA_PATH = Paths.get("data/fileTreeTest");
    private SoftAssert softAssertion = new SoftAssert();

    @Test(description = "Verify how to create a separator for the file name.")
    public void makeTabs() {
        assertEquals(new Actions().makeTabs(5), "|\t\t\t\t\t",
                "The string for separating files received from the method is incorrect:");
    }

    @Test(description = "Verify how to create a separator for the directory name.")
    public void makeDirSeparators() {
        assertEquals(new Actions().makeDirSeparators(5), "--\t--\t--\t--\t--\t",
                "The string for separating directories received from the method is incorrect:");
    }

    @Test(description = "Verify how file name string clipping works.")
    public void getFileNameFromString() {
        softAssertion.assertEquals(new Actions().getFileNameFromString("|\t\t\t\t_String filename.txt"),
                "_String filename.txt",
                "Name string clipping works was done incorrectly, we got a bad string:");
        softAssertion.assertEquals(new Actions().getFileNameFromString("_"), "_",
                "Name string clipping works was done incorrectly, we got a bad string:");
        softAssertion.assertAll();
    }

    @Test(description = "Verify if the resulting string contain a directory name.")
    public void isStringContainedDirectory() {
        softAssertion.assertTrue(new Actions().isStringContainedDirectory("|--\t--\t--\tdirectoryName"),
                "Method does not work correctly, a string containing the directory name is sent in the parameter, but result false.");
        softAssertion.assertFalse(new Actions().isStringContainedDirectory("|\tsomeFileName"),
                "Method does not work correctly, a string containing the file name is sent in the parameter, but result true.");
        softAssertion.assertAll();
    }

    @Test(description = "Verify if the resulting string is contain file name.")
    public void isStringContainedFiles() {
        softAssertion.assertTrue(new Actions().isStringContainedFiles("|\tsomeFileName"),
                "Method does not work correctly, a string containing the file name is sent in the parameter, but result false.");
        softAssertion.assertFalse(new Actions().isStringContainedFiles("|--\t--\t--\tdirectoryName"),
                "Method does not work correctly, a string containing the directory name in the parameter, but result true.");
        softAssertion.assertAll();
    }

    @Test(description = "Verify that the string does not consist of just one char \"|\", length of string > 0 and first" +
            " char os string is \"|\".")
    public void isStringCorrect() {
        softAssertion.assertTrue(new Actions().isStringCorrect("|_12345"), "Method work incorrect, for this " +
                "test string result must be true.");
        softAssertion.assertFalse(new Actions().isStringCorrect("|"), "Method work incorrect, for this test " +
                "string result must be false.");
        softAssertion.assertFalse(new Actions().isStringCorrect(""), "Method work incorrect, for this test " +
                "string result must be false.");
        softAssertion.assertAll();
    }

    @Test(description = "Verify method receiving answers to the second part of the task.")
    public void getAnswersForPartTwoMainTask() {
        int[] arrayWithAnswers = new Actions().getAnswersForPartTwoMainTask(TEST_DATA_PATH);
        softAssertion.assertEquals(arrayWithAnswers.length, 4, "Received incorrect array with data:, length ");
        softAssertion.assertEquals(arrayWithAnswers[NUMBER_OF_DIRECTORIES], 5,
                "Received incorrect number of directories for this test data.");
        softAssertion.assertEquals(arrayWithAnswers[NUMBER_OF_FILES], 26,
                "Received incorrect number of files for this test data.");
        softAssertion.assertEquals(arrayWithAnswers[AVERAGE_NUMBER_FILES], 5,
                "Received incorrect average number of files for this test data.");
        softAssertion.assertEquals(arrayWithAnswers[AVERAGE_LENGTH_FILE_NAMES], 12,
                "Received incorrect average length file names for this test data.");
        softAssertion.assertAll();
    }
}