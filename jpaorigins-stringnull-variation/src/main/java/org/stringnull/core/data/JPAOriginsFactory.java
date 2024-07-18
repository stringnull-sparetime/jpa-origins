package org.stringnull.core.data;

import org.stringnull.core.data.query.FindByIdHandler;
import org.stringnull.core.data.query.SaveHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JPAOriginsFactory {
    public <T,ID> JPAOriginsCrudRepository build(Class<T> entity) {

        JPAOriginsCrudRepositoryInvocationHandler<T,ID> crpHandler = new JPAOriginsCrudRepositoryInvocationHandler<>();

        Method methodFindById = Arrays.stream(JPAOriginsCrudRepository.class.getDeclaredMethods()).filter(method -> method.getName().equals("findById")).collect(Collectors.toSet()).stream().findFirst().get();
        crpHandler.addMethodHandler(methodFindById, new FindByIdHandler());

        Method methodSave = Arrays.stream(JPAOriginsCrudRepository.class.getDeclaredMethods()).filter(method -> method.getName().equals("save")).collect(Collectors.toSet()).stream().findFirst().get();
        crpHandler.addMethodHandler(methodSave, new SaveHandler());

        return (JPAOriginsCrudRepository<T,ID>) Proxy.newProxyInstance(
                entity.getClassLoader(),
                new Class<?>[]{JPAOriginsCrudRepository.class},
                crpHandler
        );

    }
}
