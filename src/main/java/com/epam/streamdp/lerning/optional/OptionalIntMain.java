package com.epam.streamdp.lerning.optional;

import java.util.OptionalInt;

public class OptionalIntMain {
    public static void main(String[] args) {
        OptionalInt optionalInt = OptionalInt.of(1);
        int value = optionalInt.getAsInt();

        OptionalInt optional = OptionalInt.empty();
        int value2 = optional.orElse(777);
        System.out.println(value);
        System.out.println(value2);

    }

}
