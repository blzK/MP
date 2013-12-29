/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bpertev
 */
public class GenericProxy<T> {

    public T createProxy(final T foo, final Logger logger) throws IOException {



        Proxy proxy = (Proxy) Proxy.newProxyInstance(foo.getClass().getClassLoader(),
                new Class[]{foo.getClass()},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.log(Level.FINEST, "Entering Method "+ method.toString());
                        return proxy.getClass().getMethod(method.toString(), (Class<?>[]) args);
                    }
                });

        return (T) proxy;
    }

}
