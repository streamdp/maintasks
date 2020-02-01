package com.epam.streamdp.one;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class ActionsTest {
    int[] arrayOfInts = {123, 345, 432, 123452132, 65433, 222, 3345, 55, 33, 222, 3, 444, 55};
    String stringForMakingArrayOfInt = "123 345 432 123452132 65433 222 3345 55 33 222 3 444 55";

    @Test
    public void testFindpositionInArrayIntWithMinLength() {
        assertTrue(Actions.findpositionInArrayIntWithMinLength(arrayOfInts) == 10);
    }

    @Test
    public void testFindPositionInArrayIntWithMaxLength() {
        assertTrue(Actions.findPositionInArrayIntWithMaxLength(arrayOfInts) == 3);
    }

    @Test
    public void testFillArrayLengthInteger() {
        int[] arrayLengthIntegers = Actions.fillArrayLengthInteger(arrayOfInts);
        assertTrue(arrayLengthIntegers[0] == 3 && arrayLengthIntegers[3] == 9 && arrayLengthIntegers[10] == 1);
    }

    @Test
    public void testMakeArrayIntFromString() {
        int[] testArrayForMakingArrayFromString = Actions.makeArrayIntFromString(stringForMakingArrayOfInt);
        assertTrue(Arrays.equals(arrayOfInts, testArrayForMakingArrayFromString));
    }

    @Test
    public void testGetLenghtNumber() {
        assertTrue(Actions.getLenghtNumber(arrayOfInts[3]) == 9);
    }

    @Test
    public void testGetCountSameDigitInNumber() {
        assertTrue(Actions.getCountSameDigitInNumber(arrayOfInts[5], 3) == 3);
    }
}