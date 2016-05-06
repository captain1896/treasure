package com.buzz.test;

public enum Value {
    TRUE(1),FLASE(0);
    private int value;

    private Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
