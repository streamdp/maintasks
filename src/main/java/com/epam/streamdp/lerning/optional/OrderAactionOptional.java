package com.epam.streamdp.lerning.optional;

import com.epam.streamdp.lerning.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderAactionOptional {
    public Optional<Order> findById(List<Order> orders, long id) {
        List<Order> result = orders.stream().filter(o -> id == o.getOrderId()).collect(Collectors.toList());
        Optional<Order> optionalOrder = result.size() !=0 ? Optional.of(result.get(0)):Optional.empty();
        return optionalOrder;
    }
    public Optional<Order> findByIdOptional(List<Order> orders, long id) {
        Optional<Order> optionalOrder = orders.stream().filter(o -> id == o.getOrderId()).findAny();
        return optionalOrder;
    }
}
