package org.stringnull.core.data.query;

import org.stringnull.core.data.JPAOriginsCrudRepositoryInvocationHandler;

public class SaveHandler implements JPAOriginsCrudRepositoryInvocationHandler.MethodHandler{
    @Override
    public Object invoke(Object[] args) {
        System.out.println("INSERT INTO <entity> values...");
        return null;
    }
}
