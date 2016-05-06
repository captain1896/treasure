package com.buzz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 4/8/16.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);
        System.getProperties().list(System.out);
        String username = System.getProperty("user.name");
        String customize = System.getProperty("user.customize");
        System.out.println(customize);
        System.out.println(username);
    }
}
