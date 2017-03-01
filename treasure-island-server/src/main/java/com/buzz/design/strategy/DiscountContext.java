package com.buzz.design.strategy;


public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public Double getDiscountPrice(double price) {
        if (strategy == null) {
            strategy = new DefaultDiscount();
        }

        return strategy.getDiscount(price);
    }

    public DiscountStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
}
