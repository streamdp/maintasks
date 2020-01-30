package com.epam.streamdp.lerning.collection;

//import java.util.*;
//public class Test{
//    public static void main(String[] a){
//        Set<Object> set = new LinkedHashSet<>();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add(set);
//        System.out.println(set);            // 1
//
//    }
//}

import java.util.*;
public class Test {
    public static void main(String[] a) {
        Map<String, Object> m1 = new HashMap<>();
        m1.put("k1","s");
        Map<String, Object> m2 = new HashMap<>(m1);
        m1.put("k2", m2);
        Map<String, Object> m3 = new HashMap<>(m1);
        m2.put("k2",m3);
        m1.put("k3",m2);
        System.out.print(m1);
    }
}