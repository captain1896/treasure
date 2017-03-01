package com.buzz.aop;

import java.util.UUID;

public class UUIDUtilTest {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString();
        String newRandomString = randomString.replace("-", "");
        System.out.println(randomString);
        System.out.println(newRandomString);

    }
}
