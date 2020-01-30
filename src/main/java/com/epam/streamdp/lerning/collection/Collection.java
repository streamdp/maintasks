package com.epam.streamdp.lerning.collection;

import java.util.HashSet;
import java.util.Set;

public class Collection {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>(){
            {
                this.add("one");
                this.add("two");
                this.add("ten");
            }
        };
        System.out.println(set);
    }
}
