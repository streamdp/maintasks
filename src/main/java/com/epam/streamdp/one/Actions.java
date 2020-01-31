package com.epam.streamdp.one;

import java.util.Scanner;

public class Actions {
    private Actions() {
        throw new IllegalStateException("Optional task one Actions.class");
    }

    public static int findpositionInArrayIntWithMinLength(int[] arrayWithLengthInt){
        int minLengthOfInt = Integer.MAX_VALUE;
        int positionInArrayIntWithMinLength = 0;
        for (int i = 0; i < arrayWithLengthInt.length ; i++) {
            if (arrayWithLengthInt[i] < minLengthOfInt) {
                minLengthOfInt = arrayWithLengthInt[i];
                positionInArrayIntWithMinLength = i;
            }
        }
        return positionInArrayIntWithMinLength;
    }

     public static int findPositionInArrayIntWithMaxLength(int[] arrayWithLengthInt){
        int maxLengthOfInt = 0;
        int positionInArrayIntWithMaxLength = 0;
        for (int i = 0; i < arrayWithLengthInt.length; i++) {
            if (arrayWithLengthInt[i] > maxLengthOfInt) {
                maxLengthOfInt = arrayWithLengthInt[i];
                positionInArrayIntWithMaxLength = i;
            }

        }
        return positionInArrayIntWithMaxLength;
    }

    public static int[] fillArrayLengthInteger(int [] arrayOfInt){
        int[] arrayWithLengthInt = new int[arrayOfInt.length];
        for (int i = 0; i < arrayOfInt.length; i++) {
            arrayWithLengthInt[i] = getLenghtNumber(arrayOfInt[i]);
        }
        return arrayWithLengthInt;
    }
    
    public static int[] makeArrayIntFromString(String stringWithInts) {
        Scanner arrayOfIntsFromString = new Scanner(stringWithInts);
        int countOfInts = stringWithInts.split("\\s").length;
        int[] arrayOfInt = new int[countOfInts];
        for (int i = 0; i < countOfInts; i++) {
            arrayOfInt[i] = arrayOfIntsFromString.nextInt();
        }
        arrayOfIntsFromString.close();
        return arrayOfInt;
    }

    public static int getLenghtNumber(int numberLookingForTheLength) {
        int counterLookingForTheLength = 0;
        while (numberLookingForTheLength != 0) {
            numberLookingForTheLength /= 10;
            counterLookingForTheLength++;
        }
        return counterLookingForTheLength;
    }

    public static int getCountSameDigitInNumber(int numberLookingForTheSameDigit, int numLength) {
        int countSameDigit = numLength == 2 ? 1 : 0;
        int[] arrayOfInts = new int[numLength];
        for (int i = 0; i < arrayOfInts.length; i++) {
            arrayOfInts[i] = numberLookingForTheSameDigit % 10;
            numberLookingForTheSameDigit /= 10;
        }
        for (int i = 0; i < arrayOfInts.length ; i++) {
            for (int j = i; j < arrayOfInts.length - 1; j++) {
                if (arrayOfInts[i] == arrayOfInts[j + 1]) {
                    countSameDigit++;
                }
            }
        }
        return countSameDigit;
    }
}
