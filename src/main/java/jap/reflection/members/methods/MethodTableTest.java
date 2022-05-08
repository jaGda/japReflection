package jap.reflection.members.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, square);
        printTable(1, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, Method method) {
        System.out.println(method);

        for (double i = from; i <= to; i++) {
            try {
                double result = (double) method.invoke(null, i);
                System.out.printf("%10.0f | %10.2f%n", i, result);
            } catch (IllegalAccessException | InvocationTargetException e) {
                // ignore
            }
        }
    }
}
