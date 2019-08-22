package com.robust.basis.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by bintao on 2017/8/14.
 * @see{http://biezhi.me/2017/07/20/keep-up-with-java8-datetime.html}
 */
public class Java8Date {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        localDate= LocalDate.of(2017, 8, 15);
        System.out.println(localDate);
        localDate = LocalDate.parse("2017-08-15");
        System.out.println(localDate);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println(tomorrow);

        LocalDate yesterday = LocalDate.now().minusDays(1);
        System.out.println(yesterday);

        LocalDate unknownDay = LocalDate.now().minus(2, ChronoUnit.DAYS);
        System.out.println(unknownDay);

        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());

        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString());
    }
}
