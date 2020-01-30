package com.epam.streamdp.lerning.collection;

import java.util.*;

public class VectorMain {
    public static void main(String[] args) {
//        Vector<String> vector = new Vector<>(777);
//        vector.add("java");
//        vector.add("belarus");
//        vector.add(1, null);
//        vector.addAll(vector);
//        System.out.println(vector);
//        vector.removeIf(e -> e == null);
//        vector.replaceAll(String::toUpperCase);
//        System.out.println(vector);
//        long size = vector.stream().count();
//        System.out.println(size);
//        Enumeration<String> enumeration = vector.elements();
//        while (enumeration.hasMoreElements()){
//            System.out.println(enumeration.nextElement());
//    }
        Vector<String> vct = new Vector<String>();
        vct.add("One");
        vct.add("Two");
        vct.add("Three");
        vct.add("Four");
        vct.add("Five");
        vct.add("Six");
        Enumeration enm = vct.elements();
        vct.remove(0);
        System.out.println(enm.nextElement());
         }
    }

