package com.epam.streamdp.one;

import java.util.Scanner;

import static com.epam.streamdp.one.Actions.*;

public class OptionalTaskOne {
    public static void main(String[] args) {
        System.out.println("Hello! Please, type many a few Integers separated by space:");
        Scanner scanner = new Scanner(System.in);
        String stringWithInt = scanner.nextLine();
        scanner.close();

        int[] arrayOfInt = makeArrayIntFromString(stringWithInt);

//1. Find the shortest and longest number. Print the found numbers and their length.
        int[] arrayWithLengthInt = fillArrayLengthInteger(arrayOfInt);

        int positionInArrayIntWithMaxLength = findPositionInArrayIntWithMaxLength(arrayWithLengthInt);
        int positionInArrayIntWithMinLength = findpositionInArrayIntWithMinLength(arrayWithLengthInt);
        int maxLengthOfInt = arrayWithLengthInt[positionInArrayIntWithMaxLength];

        System.out.println("Answer to OptionalTask 1-1");
        System.out.print("The shortest number: ");

        int sameIntsMoreThanOne = 0;
        for (int i = 0; i < arrayWithLengthInt.length; i++) {
            if (arrayWithLengthInt[positionInArrayIntWithMinLength] == arrayWithLengthInt[i]) {
                sameIntsMoreThanOne += 1;
                if (sameIntsMoreThanOne > 1) {
                    System.out.print(", " + arrayOfInt[i]);
                } else {
                    System.out.print(arrayOfInt[i]);
                }
            }
        }

        System.out.print(". Number length: " + arrayWithLengthInt[positionInArrayIntWithMinLength]);
        System.out.print("\n");

        System.out.print("The longest number: ");
        sameIntsMoreThanOne = 0;
        for (int i = 0; i < arrayWithLengthInt.length; i++) {
            if (arrayWithLengthInt[positionInArrayIntWithMaxLength] == arrayWithLengthInt[i]) {
                sameIntsMoreThanOne += 1;
                if (sameIntsMoreThanOne > 1) {
                    System.out.print(", " + arrayOfInt[i]);
                } else {
                    System.out.print(arrayOfInt[i]);
                }
            }
        }
        System.out.println(". Number length: " + arrayWithLengthInt[positionInArrayIntWithMaxLength]);
        System.out.print("\n");
// 2. Print the numbers in ascending (descending) values of their length.
        System.out.println("Answer to OptionalTask 1-2");
        System.out.print("Ascending length: ");
        for (int i = 0; i <= maxLengthOfInt; i++) {
            for (int j = 0; j < arrayWithLengthInt.length ; j++) {
                if (arrayWithLengthInt[j] == i) {
                    System.out.print(arrayOfInt[j]+" ");
                }
            }
        }
        System.out.print("\n");
        System.out.print("Descending length: ");
        for (int i = maxLengthOfInt; i > 0; i--) {
            for (int j = 0; j < arrayWithLengthInt.length; j++) {
                if (arrayWithLengthInt[j] == i) {
                    System.out.print(arrayOfInt[j] + " ");
                }
            }
        }
        System.out.print("\n\n");
// 3. Print to the console those numbers whose length is less than (greater than) the average length for all numbers,
// as well as the length.
        System.out.println("Answer to OptionalTask 1-3");
        double averageIntLength = 0;
        for (int i = 0; i < arrayWithLengthInt.length; i++) {
            averageIntLength += arrayWithLengthInt[i];
        }
        averageIntLength = Math.round(averageIntLength / arrayWithLengthInt.length);
        System.out.println("Output arrayOfInt longer than average length (" + averageIntLength + "):");
        for (int i = 0; i < arrayWithLengthInt.length; i++) {
            if (arrayWithLengthInt[i] > averageIntLength) {
                System.out.println(arrayOfInt[i] + "\tLength = " + arrayWithLengthInt[i]);
            }
        }
        System.out.print("\n");
//4. Find a number in which the number of different digits is minimal. If there are several such numbers, find the first one.
        System.out.println("Answer to OptionalTask 1-4");
        for (int i = 0; i < arrayOfInt.length ; i++) {
            if (getCountSameDigitInNumber(arrayOfInt[i], arrayWithLengthInt[i]) / arrayWithLengthInt[i] > 0 || arrayWithLengthInt[i] == 1) { //count the number of identical numbers and reduce to the length of the number
                System.out.println("Number output: " + arrayOfInt[i]);                 //if more than zero or the number consists of one digit, print the number and exit because condition to find the first.
                break;
            }
        }
    }
}

