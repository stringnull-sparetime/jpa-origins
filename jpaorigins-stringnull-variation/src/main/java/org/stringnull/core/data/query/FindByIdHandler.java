package org.stringnull.core.data.query;

import org.stringnull.core.data.JPAOriginsCrudRepositoryInvocationHandler;

public class FindByIdHandler implements JPAOriginsCrudRepositoryInvocationHandler.MethodHandler {

    //add constructor that will handle the entity manager

    @Override
    public Object invoke(Object[] args) {
        System.out.println("SELECT * FROM <entity table> where id=<id>");
        return null;
    }
}
