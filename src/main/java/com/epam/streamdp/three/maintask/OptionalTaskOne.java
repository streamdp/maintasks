package com.epam.streamdp.three.maintask;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

//Задания из раздела "Additional Unit" являются вспомогательными для курса "Коллекции". В процессе изучения разделов курса рекомендуется решить 3-5 задач из списка.
// 1.   Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
// 2.   Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.
// 3.   Создать список из элементов каталога и его подкаталогов.
public class OptionalTaskOne {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OptionalTaskOne.class.getName());

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();

        List<String> stringList = new ArrayList<>();
        String filename = "data//optionaltask.txt";
        readFile(stringList, filename);
        Collections.reverse(stringList);
        writeFile(stringList, filename);
        logger.log(Level.INFO, "Revers done! Please, check file.");
    }

    public static void readFile(List<String> stringList, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                stringList.add(reader.readLine());
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public static void writeFile(List<String> stringList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String string : stringList) {
                writer.write(string + '\n');
            }
            writer.flush();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }

    }

}

