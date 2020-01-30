package com.epam.streamdp.lerning.optional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateGen {
    public static void main(String[] args) {
        Date theEnd = new Date(Long.MAX_VALUE);
        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.LONG);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        String text = dateFormat.format(theEnd);
        System.out.println(text);


        Date moment = new Date(); // Задаем количество миллисекунд Unix-time с того-самого-момента
        System.out.println(moment);
        moment.getTime(); // Узнаем количество миллисекунд Unix-time с того-самого-момента.
        System.out.println(moment);
        moment.setTime(1111111111111L);
    }
}
