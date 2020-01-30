package com.epam.streamdp.lerning.collection;

import java.util.*;
import java.util.Collection;

enum Counter{
    UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE
}

//public class Quest {
////    public static void main (String args[]) {
//////        Object ob = new HashSet();
//////        System.out.print((ob instanceof Set) + ", ");
//////        System.out.print(ob instanceof SortedSet);
////        Point point1 = new Point(1);
////        Point point2 = new Point(1);
////        Set<Point> set = new HashSet<Point>();
////        set.add(point1);
////        set.add(point2);
////        System.out.println(set.size());
////    }
////
////        public static void main(String[] args){
////            EnumSet<Counter> countSet1 = EnumSet.range(Counter.TRES, Counter.CINCO); // 1
////            EnumSet<Counter> countSet2 = EnumSet.complementOf(countSet1 ); // 2
////            System.out.println(countSet2 );
////        }
//
////}
//
//public class Quest {
//    public static void main(String[] args) {
//        NavigableMap<String, Number> nmap = new TreeMap<String, Number>();
//        nmap.put("one", new Integer(1));
//        nmap.put("two", new Integer(2));
//        nmap.put("three", new Integer(3));
//        nmap.put("four", new Integer(4));
//        Map<String, Number> map = nmap.headMap("three");
//        System.out.println(map);
//
//    }
//}
public class Quest {
    public static void main(String[] args) {
        AbstractSequentialList<String> l = new LinkedList<>();    //1
        l.add(2,"s");                    //2
        Collection<String> c = l;                    //3
        Iterator i = c.iterator();
        ListIterator li = (ListIterator)i;            //4
        while(li.hasNext())
            System.out.println(li.next());
    }
}