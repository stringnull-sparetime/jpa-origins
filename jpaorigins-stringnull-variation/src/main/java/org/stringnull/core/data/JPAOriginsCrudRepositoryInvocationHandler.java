package org.stringnull.core.data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JPAOriginsCrudRepositoryInvocationHandler<T,ID> implements InvocationHandler {
    private final Map<Method, MethodHandler> methodHandlers = new HashMap<>();

    public void addMethodHandler(Method method, MethodHandler mhandler){
       methodHandlers.put(method, mhandler);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("METHOD CALLED: " + method.getName());
        System.out.println("ARGUMENTS:" + Arrays.stream(method.getParameters()).findFirst().toString());

        if(methodHandlers.containsKey(method)){
            MethodHandler methodHandler =  methodHandlers.get(method);
            methodHandler.invoke(args);
        }

        return null;//new Object();
    }

    public interface MethodHandler {
       public Object invoke(Object args[]);
    }
}
