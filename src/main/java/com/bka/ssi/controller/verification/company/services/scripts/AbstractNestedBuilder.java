package com.bka.ssi.controller.verification.company.services.scripts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractNestedBuilder<T, V> implements Buildable<V> {

    protected T parent;
    protected String methodIdentifierForParent;

    public T done()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> parentClass = parent.getClass();

        V build = this.build();

        /* TODO - remove if workaround plays out, workaround removes hacked code parts */
        //String methodName = "with" + build.getClass().getSimpleName();
        String methodName = "with" + this.methodIdentifierForParent;

        Method method = parentClass.getDeclaredMethod(methodName, build.getClass());
        method.invoke(parent, build);

        return parent;
    }

    /* TODO - BKAACMGT-125 - should not be accessible from outside builders */
    public <P extends AbstractNestedBuilder<T, V>> P withParentBuilder(T parent) {
        this.parent = parent;
        return (P) this;
    }
}
