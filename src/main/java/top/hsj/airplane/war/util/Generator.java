package top.hsj.airplane.war.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hsj
 */
public interface Generator<T> {
    T next() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
