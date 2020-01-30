package com.epam.streamdp.lerning.collection;

import java.util.ArrayList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(42);
        list.add(null);
        list.add(42);
        list.add(77);
        System.out.println(list);
        list.add(5,87);
        System.out.println(list);
//        Integer value = list.get(3);
//        System.out.println(value);
//        int index = list.indexOf(7);
//        System.out.println(index);0
        list.remove(Integer.valueOf(42));
        System.out.println(list);
        System.out.println(list.subList(1,3));
    }

}
