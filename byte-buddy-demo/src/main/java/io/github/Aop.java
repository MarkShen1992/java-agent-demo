package io.github;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * byte buddy AOP功能实现，AOP的实现方式有多少种？ 1. 继承，重写要实现AOP的方法 2. 通过动态代理实现 3. 通过修改字节码的方式实现
 * 
 * @author make
 */
public class Aop {
    @LoggerAdvisor.Log
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }

    public static void main(String[] args) throws Exception {
        Aop aop =
            new ByteBuddy().subclass(Aop.class).method(ElementMatchers.any()).intercept(Advice.to(LoggerAdvisor.class))
                .make().load(Aop.class.getClassLoader()).getLoaded().newInstance();
        aop.bar(123);
        aop.foo(456);
    }
}

class LoggerAdvisor {
    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments,
        @Advice.Return Object ret) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println(
                "Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Log {}
}
