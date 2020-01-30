package com.epam.streamdp.one;

import java.util.Scanner;

public class OptionalTaskOne {
    public static void main(String[] args) {
        System.out.println("Hello! Please, type many a few Integers separated by space:");
        Scanner scanner = new Scanner(System.in);
        String stringWithInt = scanner.nextLine();
        scanner.close();

        int[] arrayOfInt = makeArrayIntFromString(stringWithInt);

//1.     Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
        int[] arrayWithLenghtInt = new int[arrayOfInt.length];
        int maxLengthOfInt = 0;
        int positionInArrayIntWithMaxLength = 0;
        int minLengthOfInt = Integer.MAX_VALUE;
        int positionInArrayIntWithMinLength = 0;

        for (int i = 0; i < arrayOfInt.length; i++) {
            arrayWithLenghtInt[i] = getLenghtNumber(arrayOfInt[i]);
            if (arrayWithLenghtInt[i] > maxLengthOfInt) {
                maxLengthOfInt = arrayWithLenghtInt[i];
                positionInArrayIntWithMaxLength = i;
            } else if (arrayWithLenghtInt[i] < minLengthOfInt) {
                minLengthOfInt = arrayWithLenghtInt[i];
                positionInArrayIntWithMinLength = i;
            }
        }

        System.out.println("Answer to OptionalTask 1-1");
        int sameIntsMoreThanOne = 0;
        System.out.print("The shortest number: ");
        for (int i = 0; i < arrayWithLenghtInt.length; i++) {
            if (arrayWithLenghtInt[positionInArrayIntWithMinLength] == arrayWithLenghtInt[i]) {
                sameIntsMoreThanOne += 1;
                if (sameIntsMoreThanOne > 1) {
                    System.out.print(", " + arrayOfInt[i]);
                } else {
                    System.out.print(arrayOfInt[i]);
                }
            }
        }

        System.out.print(". Number length: " + arrayWithLenghtInt[positionInArrayIntWithMinLength]);
        System.out.print("\n");

        System.out.print("The longest number: ");
        sameIntsMoreThanOne = 0;
        for (int i = 0; i < arrayWithLenghtInt.length; i++) {
            if (arrayWithLenghtInt[positionInArrayIntWithMaxLength] == arrayWithLenghtInt[i]) {
                sameIntsMoreThanOne += 1;
                if (sameIntsMoreThanOne > 1) {
                    System.out.print(", " + arrayOfInt[i]);
                } else {
                    System.out.print(arrayOfInt[i]);
                }
            }
        }
        System.out.println(". Number length: " + arrayWithLenghtInt[positionInArrayIntWithMaxLength]);
        System.out.print("\n");
//2.     Вывести числа в порядке возрастания (убывания) значений их длины.
        System.out.println("Answer to OptionalTask 1-2");
        System.out.print("Ascending length: ");
        for (int i = 0; i <= maxLengthOfInt; i++) {
            for (int j = 0; j < arrayWithLenghtInt.length ; j++) {
                if (arrayWithLenghtInt[j] == i) {
                    System.out.print(arrayOfInt[j]+" ");
                }
            }
        }
        System.out.print("\n");
        System.out.print("Descending length: ");
        for (int i = maxLengthOfInt; i > 0; i--) {
            for (int j = 0; j < arrayWithLenghtInt.length ; j++) {
                if (arrayWithLenghtInt[j] == i) {
                    System.out.print(arrayOfInt[j]+" ");
                }
            }
        }
        System.out.print("\n\n");
//3.    Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.
        System.out.println("Answer to OptionalTask 1-3");
        double averageIntLength = 0;
        for (int i = 0; i < arrayWithLenghtInt.length ; i++) {
            averageIntLength +=arrayWithLenghtInt[i];
        }
        averageIntLength = Math.round(averageIntLength / arrayWithLenghtInt.length);
        System.out.println("Output arrayOfInt longer than average length ("+averageIntLength+"):");
        for (int i = 0; i < arrayWithLenghtInt.length  ; i++) {
            if (arrayWithLenghtInt[i] > averageIntLength) {
                System.out.println(arrayOfInt[i] + "\tLength = "+arrayWithLenghtInt[i]);
            }
        }
        System.out.print("\n");
//4.     Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.
        System.out.println("Answer to OptionalTask 1-4");
        for (int i = 0; i < arrayOfInt.length ; i++) {
            if (getCountSameDigitInNumber(arrayOfInt[i], arrayWithLenghtInt[i]) / arrayWithLenghtInt[i] > 0 || arrayWithLenghtInt[i] == 1) { //считаем количество одинаковых чисел и приводим к длине числа
                System.out.println("Number output: "+arrayOfInt[i]);                 //если больше ноля или число состоит из одной цифры, выводим число и выходим т.к. условие найти первое.
                break;
            }
        }
    }

    private static int[] makeArrayIntFromString(String stringWithInts) {
        Scanner arrayOfIntsFromString = new Scanner(stringWithInts);
        int countOfInts = stringWithInts.split("\\s").length;
        int[] arrayOfInt = new int[countOfInts];
        for (int i = 0; i < countOfInts; i++) {
            arrayOfInt[i] = arrayOfIntsFromString.nextInt();
        }
        arrayOfIntsFromString.close();
        return arrayOfInt;
    }

    private static int getLenghtNumber(int numberLookingForTheLength) {
        int counterLookingForTheLength = 0;
        while (numberLookingForTheLength != 0) {
            numberLookingForTheLength /= 10;
            counterLookingForTheLength++;
        }
        return counterLookingForTheLength;
    }

    private static int getCountSameDigitInNumber(int numberLookingForTheSameDigit, int numLength) {
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

