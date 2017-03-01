package com.buzz.aop;


import java.lang.reflect.Method;

public abstract class BeforeHandler extends AbstractHandler {

    public abstract void handlerBefore(Object proxy, Method method, Object[] args);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handlerBefore(proxy, method, args);
        return method.invoke(getTargetObject(), args);
    }
}
