package jap.reflection.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class ReflectiveInstantiation {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Class<? extends Set<String>> cl = null;
        try {
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName())
                    .log(Level.SEVERE, "Class not found", e);
        }

        Constructor<? extends Set<String>> constructor = null;
        try {
            assert cl != null;
            constructor = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName())
                    .log(Level.SEVERE, "No parameterless constructor", e);
        }

        Set<String> s = null;
        try {
            assert constructor != null;
            s = constructor.newInstance();
        } catch (InstantiationException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName())
                    .log(Level.SEVERE, "Class not instantiable");
        } catch (IllegalAccessException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName())
                    .log(Level.SEVERE, "Constructor not accessible");
        } catch (InvocationTargetException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName()).log(Level.SEVERE, "Constructor threw " + e.getCause());
        } catch (ClassCastException e) {
            Logger.getLogger(ReflectiveInstantiation.class.getName()).log(Level.SEVERE, "Class does not implement Set");
        }

        assert s != null;
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }
}
