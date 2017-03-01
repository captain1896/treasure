package com.buzz.design.strategy;


public class StrategyPatternTest {
    public static void main(String[] args) {
        DiscountContext context = new DiscountContext(null);
        int price = 90;
        context.getDiscountPrice(price);
        System.out.println(String.format("old price:[%s],new price:[%s]", price, context.getDiscountPrice(price)));

        context.setStrategy(new DiscountStrategy() {
            @Override
            public Double getDiscount(double price) {
                return price * 0.5;
            }
        });
        System.out.println(String.format("old price:[%s],new price:[%s]", price, context.getDiscountPrice(price)));
    }
}
