package com.epam.streamdp.three.maintask;

import com.epam.streamdp.three.actions.LoggingConfig;
import com.epam.streamdp.three.actions.SaveReadItemsFromJson;
import com.epam.streamdp.three.entity.Minerals;
import com.epam.streamdp.three.entity.Necklace;
import com.epam.streamdp.three.enums.TypeOfTransparency;

import java.util.*;
import java.util.logging.Level;
//6. Камни. Определить иерархию драгоценных и полудрагоценных камней. Отобрать камни для ожерелья.
//        Подсчитать общий вес (вкаратах) и стоимость.
//        Провести сортировку камней ожерелья на основе ценности.
//        Найти камни в ожерелье, соответствующие заданному
//        диапазону параметров прозрачности.

public class MainTask {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTask.class.getName());

   static final int ONE   = 1;
   static final int TWO   = 2;
   static final int THREE = 3;
   static final int FOUR  = 4;
   static final int FIVE  = 5;
   static final int SIX   = 6;

   static final String LIST_MENU_ENTRY = "\nPlease select an action number.\n" +
           "1. Create necklace from gems.\n" +
           "2. Create necklace from semiprecious gems.\n" +
           "3. Create necklace from mixed minerals.\n" +
           "4. Calculate total weight & cost created necklace.\n" +
           "5. Sort necklace stones by worth.\n" +
           "6. Find stones by the range of transparency parameters.\n" +
           "0. Exit";
   static final String INPUT_COUNT_ITEMS = "Please input count items in necklace:";
   static final String SORTING_NECKLACE_STONES_BY_WORTH = "Sorting necklace stones by worth (BYN/ct):";
   static final String SPECIFY_TRANSPARENCY_PARAMETERS = "Specify transparency options:\n" +
           "0 - TRANSPARENT\n" +
           "1 - TRANSLUCENT\n" +
           "2 - OPAQUE\n";
   static final String INPUT_MIN = "Input min value:";
   static final String INPUT_MAX = "Input max value";
   static final String INPUT_CORRECT_NUMBER = "Please, input correct number!";
   static final String TOTAL_WEIGHT_NECKLACE = "Total weight necklace = {0} ct";
   static final String TOTAL_COST_NECKLACE = "Total cost   necklace = {0} BYN";


    public static void main(String[] args) {
        new LoggingConfig().LoggingConfig();
        List<Minerals> minerals = SaveReadItemsFromJson.loadItemsFromFile("items.json");
        Necklace necklace = new Necklace(minerals);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(LIST_MENU_ENTRY);
            int taskNumber = isTheTaskNumberEnteredCorrectly(scanner) ? Math.abs(scanner.nextInt()) : 0;
            if (taskNumber == 0) {break;}
            switch (taskNumber){
                case ONE:
                    logger.log(Level.INFO,INPUT_COUNT_ITEMS);
                    necklace = new Necklace(minerals);
                    necklace = new Necklace(necklace.getGemsNecklaceRandomized(isTheTaskNumberEnteredCorrectly(scanner) ? Math.abs(scanner.nextInt()):10));
                    System.out.println(necklace);
                    break;
                case TWO:
                    logger.log(Level.INFO,INPUT_COUNT_ITEMS);
                    necklace = new Necklace(minerals);
                    necklace = new Necklace(necklace.getSemiPreciousNecklaceRandomized(isTheTaskNumberEnteredCorrectly(scanner) ? Math.abs(scanner.nextInt()):10));
                    System.out.println(necklace.toString());
                    break;
                case THREE:
                    necklace = new Necklace(minerals);
                    logger.log(Level.INFO,INPUT_COUNT_ITEMS);
                    necklace = new Necklace(necklace.getMixedNecklace(isTheTaskNumberEnteredCorrectly(scanner) ? Math.abs(scanner.nextInt()):10));
                    System.out.println(necklace);
                    break;
                case FOUR:
                    logger.log(Level.INFO, TOTAL_WEIGHT_NECKLACE, necklace.getTotalWeight());
                    logger.log(Level.INFO, TOTAL_COST_NECKLACE, necklace.getTotalCost());
                    break;
                case FIVE:
                    logger.log(Level.INFO, SORTING_NECKLACE_STONES_BY_WORTH);
                    System.out.println(necklace.sortMineralsByWorth());
                    break;
                case SIX:
                    logger.log(Level.INFO, SPECIFY_TRANSPARENCY_PARAMETERS);
                    logger.log(Level.INFO, INPUT_MIN);
                    int min = isTheTaskNumberEnteredCorrectly(scanner) ? scanner.nextInt():2;
                    logger.log(Level.INFO, INPUT_MAX);
                    int max = isTheTaskNumberEnteredCorrectly(scanner) ? scanner.nextInt():0;
                    System.out.println(necklace.findMineralsByTransparency(TypeOfTransparency.values()[min],TypeOfTransparency.values()[max]));
                    break;
                default:logger.log(Level.WARNING, INPUT_CORRECT_NUMBER);
            }
        }
    }
    public static boolean isTheTaskNumberEnteredCorrectly(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            logger.log(Level.INFO,INPUT_CORRECT_NUMBER);
            scanner.next();
        }
        return true;
    }
}

