package com.epam.streamdp.three.maintask;

import com.epam.streamdp.three.actions.LoggingConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class OptionalTaskTwo {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OptionalTaskTwo.class.getName());
    public static void main(String[] args) {
        new LoggingConfig();

        logger.log(Level.INFO,"Please, enter an Integer:");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            logger.log(Level.INFO,"Please, enter an correct Integer:");
            scanner.next();
        }

        Integer integer = scanner.nextInt();
        char[] charsArray = integer.toString().toCharArray();
        List<Character> integerStack = new ArrayList<>(charsArray.length);

        boolean wasItPositiveInteger = true;

        for (int i = 0; i < charsArray.length ; i++) {
            if (charsArray[i] != '-' ) {
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

        integer = wasItPositiveInteger? Integer.parseUnsignedInt(integerString.toString()) : Integer.parseInt(integerString.toString()) * -1;

        logger.log(Level.INFO, "Answer the question: {0}", integer);
    }
}