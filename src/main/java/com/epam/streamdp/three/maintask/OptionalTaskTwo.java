package com.epam.streamdp.three.maintask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OptionalTaskTwo {
//    2.   Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.
    public static void main(String[] args) {

        System.out.println("Please, enter an Integer:");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            System.out.println("Please, enter an correct Integer:");
            scanner.next();
        }

        Integer integer = scanner.nextInt();
        char[] charsArray = integer.toString().toCharArray();
        List<Character> integerStack = new ArrayList<>(charsArray.length);

        boolean wasItPositiveInteger = true;

        for (int i = 0; i < charsArray.length ; i++) {
            if (charsArray[i] != '-') {
                integerStack.add(charsArray[i]);
            } else {
                wasItPositiveInteger = false;
            }
        }

        Collections.reverse(integerStack);
        StringBuilder integerString = new StringBuilder();

        for (Character character:integerStack){
            integerString.append(character.charValue());
        }

        System.out.println(wasItPositiveInteger? Integer.parseUnsignedInt(integerString.toString()) : Integer.parseInt(integerString.toString()) * -1);
    }
}