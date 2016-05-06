package com.buzz.util;

import java.time.LocalDateTime;

public class DateTimeUtilTest {
    public static void main(String[] args) {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        LocalDateTime localDateTime = dateTimeUtil.toLocalDateTime(1461722655L);
        System.out.println("localDateTime=" + localDateTime);
    }

}
