package jap.reflection.classes;

import java.lang.reflect.Field;

public class RetrievingClassObject {
    public static void main(String[] args) throws NoSuchFieldException {
        /**
         * Użyj metod Class.getDeclaringClass() vs Class.getEnclosingClass()
         * na interfejsie wewnętrznym i polu w klasie Example
         */
        Class<?> cl = Example.class;
        Field field = cl.getField("anInterface");
        System.out.println(field.getDeclaringClass());

        Example.InnerInterface<String> innerInterface = new Example().blah();
        System.out.println(innerInterface.getClass().getEnclosingClass());
    }
}

class Example {
    public InnerInterface<Integer> anInterface = System.out::println;

    InnerInterface<String> blah() {
        return new InnerInterface<String>() {
            @Override
            public void doSomething(String x) {
                System.out.println(x);
            }
        };
    }

    interface InnerInterface<T> {
        void doSomething(T s);
    }
}

