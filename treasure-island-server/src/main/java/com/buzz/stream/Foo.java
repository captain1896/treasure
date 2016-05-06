package com.buzz.stream;

import java.util.ArrayList;
import java.util.List;

public class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", bars=" + bars +
                '}';
    }
}
