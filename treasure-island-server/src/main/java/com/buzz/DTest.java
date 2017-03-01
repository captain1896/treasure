package com.buzz;

import java.util.Random;

public class DTest {
    private static final int MAX_COUNT = 50;

    public static void main(String[] args) {
        System.out.println(getRandomNo(54));
        System.out.println(getRandomNo(0));
        Random random = new Random();
        for (int i = 0 ; i < 10 ;i++){
            System.out.println(random.nextInt(20));
        }
    }

    private static int getRandomNo(int count) {
        if (count > MAX_COUNT) {
            int max = count - MAX_COUNT;
            int min = 1;
            Random random = new Random();
            int rd = random.nextInt(max) % (max - min) + min;
            return rd;
        } else {
            return 0;
        }
    }
}
