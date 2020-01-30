package com.epam.streamdp.lerning.collection;

import static com.epam.streamdp.lerning.collection.Country.*;

import java.util.EnumMap;

public class EnumSetCountryMain {
    public static void main(String[] args) {
//        EnumSet<Country> asiaCountriers = EnumSet.of(ARMENIA, INDIA, KAZAKHSTAN);
//        String nameCountry = "India";
//        Country current = Country.valueOf(nameCountry.toUpperCase());
//        if (asiaCountriers.contains(current)){
//            System.out.println("Asia Action");
//        }
//        asiaCountriers.forEach(c -> c.grow(1));
        EnumMap<Country, Integer> map = new EnumMap<Country, Integer>(Country.class);
        map.put(POLAND, 8);
        map.put(UKRAINE,1);
        map.put(BELARUS,0);


    }
}
