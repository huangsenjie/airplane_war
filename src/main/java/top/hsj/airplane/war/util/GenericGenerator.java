package top.hsj.airplane.war.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hsj
 */
public class GenericGenerator<T> implements Generator<T> {
    private Class<T> type;

    public GenericGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next(){
        T next;
        try {
            next = type.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return next;
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new GenericGenerator<>(type);
    }

}
