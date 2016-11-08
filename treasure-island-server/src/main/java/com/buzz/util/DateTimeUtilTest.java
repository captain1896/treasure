package com.buzz.util;

import java.time.LocalDateTime;

public class DateTimeUtilTest {
    public static void main(String[] args) {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        LocalDateTime localDateTime = dateTimeUtil.toLocalDateTime(1462803831L);
        System.out.println("localDateTime=" + localDateTime);
        for (String s : "dsdsd_dsds".split("_")) {
            System.out.println(s);
        }
    }

}
