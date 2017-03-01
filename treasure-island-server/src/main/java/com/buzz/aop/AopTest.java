package com.buzz.aop;


import com.buzz.aop.impl.AfterHandlerImpl;
import com.buzz.aop.impl.BeforeHandlerImpl;
import com.buzz.aop.impl.ThrowHandlerImpl;
import com.buzz.proxy.Calculator;
import com.buzz.proxy.CalculatorImpl;

import java.util.ArrayList;
import java.util.List;

public class AopTest {
    public static void main(String[] args) {
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        BeforeHandler beforeHandler = new BeforeHandlerImpl();
        AfterHandler afterHandler = new AfterHandlerImpl();
        ThrowHandler throwHandler = new ThrowHandlerImpl();
        List<AbstractHandler> handlers = new ArrayList<>();

        handlers.add(beforeHandler);
        handlers.add(afterHandler);
        handlers.add(throwHandler);
        Calculator proxy = (Calculator) ProxyFactory.getProxy(calculatorImpl, handlers);
        int result = proxy.calculate(20, 0);
        System.out.println("final result :::" + result);
    }
}
