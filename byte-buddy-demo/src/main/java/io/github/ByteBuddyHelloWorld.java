package io.github;

import java.lang.reflect.InvocationTargetException;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * byte buddy hello world
 */
public class ByteBuddyHelloWorld {

    @Override
    public String toString() {
        Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello world!")).make().load(getClass().getClassLoader()).getLoaded();
        try {
            return dynamicType.getDeclaredConstructor().newInstance().toString();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
