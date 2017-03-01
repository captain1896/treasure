package com.buzz.proxy;


import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();

        CalculatorHandler calculatorHandler = new CalculatorHandler(calculator);

        Calculator proxy = (Calculator) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), CalculatorImpl.class.getInterfaces(), calculatorHandler);

        int result = proxy.calculate(10, 5);
        System.out.println(result);
    }
}
