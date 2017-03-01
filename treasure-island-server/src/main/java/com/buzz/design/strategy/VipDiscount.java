package com.buzz.design.strategy;


public class VipDiscount implements DiscountStrategy {
    @Override
    public Double getDiscount(double price) {
        return price * 0.6;
    }
}
