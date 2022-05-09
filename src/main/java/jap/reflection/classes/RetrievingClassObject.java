package jap.reflection.classes;

import java.io.Serializable;

public class RetrievingClassObject {
    public static void main(String[] args) throws NoSuchFieldException {
        /**
         * Użyj metod Class.getDeclaringClass() vs Class.getEnclosingClass()
         * na interfejsie wewnętrznym i polu w klasie Example
         */
    }
}

class Example implements Serializable {
    InnerInterface<Integer> anInterface = System.out::println;

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

