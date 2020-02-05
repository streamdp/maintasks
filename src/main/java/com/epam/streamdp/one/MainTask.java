package com.epam.streamdp.one;

import java.util.Random;
import java.util.Scanner;

public class MainTask
{
    static final int ONE   = 1;
    static final int TWO   = 2;
    static final int THREE = 3;
    static final int FOUR  = 4;
    static final int FIVE  = 5;

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("Hello! Please, enter the program number from MainTask [1-5] or [0] for exit:");
            while (!scanner.hasNextInt()) {
                System.out.println("Please, input correct number! Number must be 1, 2, 3, 4 or 5 ...");
                scanner.next();
            }
            int taskNumber = Math.abs(scanner.nextInt());

            if (taskNumber == 0) {
                break;
            }

            switch (taskNumber) {
                case (ONE):
                    System.out.println("You entered task number 1. Please type your name:");
                    System.out.println("You name is: " + scanner.next());
                    break;
                case (TWO):
                    System.out.println("You entered task number 2. Command-line arguments are displayed in reverse order:");
                    if (args.length > 0) {
                        for (int i = args.length - 1; i >= 0; i--) {
                            System.out.println("args[" + i + "] : " + args[i]);
                        }
                    } else {
                        System.out.println("Sorry! We have no command line arguments ..");
                        System.out.println("Re-run program with few сommand-line arguments :)");
                    }
                    break;
                case (THREE):
                    System.out.println("You entered task number 3. Enter quantity of random numbers:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Number must be INT and > 0");
                        scanner.next();
                    }
                    int quantityRandomNumbers = Math.abs(scanner.nextInt());
                    System.out.println("Output using System.out.println():");
                    for (int i = 0; i < quantityRandomNumbers; i++) {
                        System.out.println(random.nextInt(10));
                    }
                    System.out.println("Output using System.out.print():");
                    for (int i = 0; i < quantityRandomNumbers; i++) {
                        System.out.print(random.nextInt(10) + " ");
                    }
                    System.out.print("\n");
                    break;
                case (FOUR):
                    System.out.println("You entered task number 4. Type a few integers as command-line arguments.");
                    if (args.length > 0) {
                        int sumOfNumbers = 0;
                        int multiplicationOfNumbers = 1;
                        for (int i = 0; i < args.length; i++) {
                            Scanner commandLineArguments = new Scanner(args[i]);
                            sumOfNumbers += commandLineArguments.hasNextInt() ? Integer.parseInt(args[i]) : 0;
                            multiplicationOfNumbers *= commandLineArguments.hasNextInt() ? Integer.parseInt(args[i]) : 1;
                            commandLineArguments.close();
                        }
                        System.out.println("sum= " + sumOfNumbers);
                        System.out.println("multiplication = " + multiplicationOfNumbers);
                    } else {
                        System.out.println("Sorry! We have no command line arguments ..");
                        System.out.println("Re-run program with few integers as command-line arguments!");
                    }
                    break;
                case (FIVE):
                    System.out.println("You entered task number 5. Enter the month number [1-12]:");
                    String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    while (!scanner.hasNextInt()) {
                        System.out.println("Number must be INT [1-12]");
                        scanner.next();
                    }
                    int receivedMonthNum = scanner.nextInt();
                    if (receivedMonthNum >= 1 && receivedMonthNum <= 12) {
                        System.out.println("Name of the month is - " + month[receivedMonthNum - 1]);
                    } else {
                        System.out.println("Sorry, you were mistaken with entering the month number. Number must be [1-12]!");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("The number should be in [1-5]! You entered: " + taskNumber);
            }
        }
    }
}
