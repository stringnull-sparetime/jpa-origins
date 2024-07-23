package org.stringnull.core.data;

import org.stringnull.core.data.query.FindAll;
import org.stringnull.core.data.query.FindByIdHandler;
import org.stringnull.core.data.query.SaveHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JPAOriginsFactory {
    public <T,ID> JPAOriginsCrudRepository build(Class<T> entity) {

        JPAOriginsCrudRepositoryInvocationHandler<T,ID> crpHandler = new JPAOriginsCrudRepositoryInvocationHandler<>();

        Method methodFindAll =
                Arrays.stream(JPAOriginsCrudRepository.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("findAll"))
                .collect(Collectors.toSet())
                .stream().findFirst().get();
        crpHandler.addMethodHandler(methodFindAll, new FindAll<T>(entity));

        Method methodFindById =
                Arrays.stream(JPAOriginsCrudRepository.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("findById"))
                .collect(Collectors.toSet())
                .stream().findFirst().get();

        crpHandler.addMethodHandler(methodFindById, new FindByIdHandler<T>(entity));

        Method methodSave =
                Arrays.stream(JPAOriginsCrudRepository.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("save"))
                .collect(Collectors.toSet()).stream().findFirst().get();

        crpHandler.addMethodHandler(methodSave, new SaveHandler<T>());

         return (JPAOriginsCrudRepository<T,ID>) Proxy.newProxyInstance(
                entity.getClassLoader(),
                new Class<?>[]{JPAOriginsCrudRepository.class},
                crpHandler
        );

    }
}
