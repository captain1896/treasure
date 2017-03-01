package com.buzz.design.strategy;


public class DefaultDiscount implements DiscountStrategy {
    @Override
    public Double getDiscount(double price) {
        return price * 0.7;
    }
}
