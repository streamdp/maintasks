package com.epam.streamdp.lerning.optional;

import com.epam.streamdp.lerning.entity.Order;

import java.util.*;

public class OptionalMain {
    public static void main(String[] args){
        List<Order> list = new ArrayList<>();
        list.add(new Order(71L,100D));
        list.add(new Order(18L,132D));
        list.add(new Order(24L,210D));
        list.add(new Order(35L,693D));
        list.add(new Order(16L,741D));
        OrderAactionOptional action = new OrderAactionOptional();
        Optional<Order> optionalOrder = action.findById(list,23);
//        Optional<Order> order = optionalOrder.or(()-> Optional.of(new Order(10,5000)));
//        System.out.println(order.get());
//        Optional<Order> orderFilter = optionalOrder.filter(o -> o.getAmount() == 210);
//        System.out.println(optionalOrder.orElse(new Order()));
//        System.out.println(optionalOrder.orElseGet(Order::new));
//        System.out.println(optionalOrder.orElseThrow());
        System.out.println(optionalOrder.orElseThrow(IllegalArgumentException::new));


//        if (optionalOrder.isPresent()) {
//            System.out.println(optionalOrder.get());
//        }
//        Set<Order> set = new HashSet<>();
//        optionalOrder.ifPresent(o -> set.add(o));
//        System.out.println(set);
    }
}
