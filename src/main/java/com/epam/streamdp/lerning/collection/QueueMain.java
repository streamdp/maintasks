package com.epam.streamdp.lerning.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMain {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>() {
            {
                this.offer("Jeans");
            }
        };
        queue.add("Dress");
        queue.offer("T-shirt");
       // queue.removeIf(s ->s.endsWith("t"));
      //  queue.forEach(System.out::println);
        queue.stream().filter(s -> !s.endsWith("t")).forEach(System.out::println);
        System.out.println();
        queue.forEach(System.out::println);
    }

}
