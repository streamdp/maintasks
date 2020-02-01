package com.epam.streamdp.two;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainTask {
    static List<Product> productList = Arrays.asList(
            new Product(1,"Coffee","4607085440118", "ООО \"Нестле Кубань\"",20.5,12,100),
            new Product(2,"Coffee","5855427977539","ООО \"Якобс Рус\"",22.5,24,99),
            new Product(3,"Coffee","4607066400391"," ООО \"Гранд - НН\"",1.5,6,77),
            new Product(4,"Coffee","4607033631742 ","ООО \"Леаланис\"",5.5,12,88),
            new Product(5,"Лампа","8727900021103","PLP S.A.",10.5,Long.MAX_VALUE,40),
            new Product(6,"Лампа","8727900021301 ","PLP S.A.",6.5,Long.MAX_VALUE,33),
            new Product(7,"Лампа","8711500363954 ","Philips Lighting Poland S.A.",1.5,Long.MAX_VALUE,42),
            new Product(8,"Клей","4600611840079","ООО \"Краски Текс\"",13.3,12,11),
            new Product(9,"Клей","4601541001585","Герметик-Трейд",15.6,18,22),
            new Product(10,"Валик","8727900021301 ","Matrize GmbH",8.5,Long.MAX_VALUE,56),
            new Product(11,"Валик","4044996020891 ","Matrize GmbH",11.5,Long.MAX_VALUE,78),
            new Product(12,"Кисть","4044996019925","Matrize GmbH",3.3,Long.MAX_VALUE,98),
            new Product(13,"Кисть","4034229000561","Kraftool I/E GmbH",5.6,Long.MAX_VALUE,32)
    );

    public static void main(String[] args) {
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("a. список товаров для заданного наименования.");

        System.out.println("Введите наименование продукта [Coffee,Лампа,Клей,Валик,Кисть]:");
        String productName = args.length > 0 ? args[0] : scannerIn.next().toLowerCase();
        List<Product> productListForAB = new Product().findProductsByName(productList, productName);
        printResults(productListForAB);

        System.out.println("\nb. список товаров для заданного наименования, цена которых не превосходит заданную.");
        System.out.println("Укажите цену:");
        double productPrice = scannerIn.hasNextDouble() ? scannerIn.nextDouble() : 0;
        printResults(new Product().findProductsByPriceLessThanThis(productListForAB, productPrice));

        System.out.println("\nc. список товаров, срок хранения которых больше заданного:");
        System.out.println("Ваедите срок хранения:");
        long productStorageTime = scannerIn.hasNextLong() ? scannerIn.nextLong() : 0;
        printResults(new Product().findProductsByStorageTimeMoreThanThis(productList, productStorageTime));

        scannerIn.close();
    }
    public static void printResults(List<Product> productList) {
        productList.forEach(System.out::println);
    }
}

