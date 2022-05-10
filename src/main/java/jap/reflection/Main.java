package jap.reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = copyOf(a, 3);
        System.out.println(Arrays.toString(a));

        System.out.println(Arrays.toString(b));
        b[0] = 123;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    static <T> T copyOf(T arr, int newLength) {
        Class<?> cl = arr.getClass();
        if (!cl.isArray()) return null;
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(arr);
        T newArray = (T) Array.newInstance(componentType, newLength);
        System.arraycopy(arr, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
