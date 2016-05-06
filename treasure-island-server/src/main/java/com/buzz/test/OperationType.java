package com.buzz.test;

public enum OperationType {
    SET_BRAND(0),SET_HEAD(1),SET_UNIVERSITY_HEAD(2);
    private int value;

    OperationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
