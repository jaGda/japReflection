package jap.reflection.classes;

import java.io.Serializable;

class RetrievingClassObjects {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Example.InnerInterface<String> i = new Example().blah();
        i.doSomething("Hello world!!");
        System.out.println(i.getClass().getEnclosingClass());
        System.out.println(i.getClass().getDeclaringClass());
    }
}

class Example implements Serializable {

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
