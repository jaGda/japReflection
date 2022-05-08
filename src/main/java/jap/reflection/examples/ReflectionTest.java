package jap.reflection.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

/**
 * Ten program wykorzystuje technikę refleksji do wydrukowania pełnych informacji o klasie.
 *
 * @author Cay Horstmann
 */
class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            out.println("Podaj nazwe klasy (np. java.util.Date): ");
            name = in.next();
        }

        try {
            Class<?> cl = Class.forName(name);
            Class<?> superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) out.print(modifiers + " ");
            out.print("class " + name);
            if (superCl != null && superCl != Object.class) out.print(" extends "
                    + superCl.getName());

            out.print("\n{\n");
            printConstructors(cl);
            out.println();
            printMethods(cl);
            out.println();
            printFields(cl);
            out.println("}");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ReflectionTest.class.getName())
                    .log(Level.SEVERE, MessageFormat.format("Class {0} not found.", name), e.getMessage());
        }
    }

    /**
     * Drukowanie wszystkich konstruktorow klasy.
     *
     * @param cl klasa
     */
    static void printConstructors(Class<?> cl) {
        Constructor<?>[] constructors = cl.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            String name = cl.getName();
            out.print("   ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) out.print(modifiers + " ");
            out.print(name + "(");

            //Drukowanie typow parametrow
            Class<?>[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) out.print(", ");
                out.print(paramTypes[i].getName());
            }
            out.println(");");
        }
    }

    /**
     * Drukuje wszystkie metody klasy.
     *
     * @param cl klasa
     */
    static void printMethods(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            Class<?> retType = method.getReturnType();
            String name = method.getName();

            out.print("   ");
            //Drukowanie modyfikatorow, typu zwrotnego i nazwy metody.
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) out.print(modifiers + " ");
            out.print(retType.getName() + " " + name + "(");

            //Drukowanie typow parametrow.
            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) out.print(", ");
                out.print(paramTypes[i].getName());
            }
            out.println(");");
        }
    }

    /**
     * Drukowanie wszystkich pol klasy.
     *
     * @param cl klasa
     */
    static void printFields(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = field.getName();
            out.print("   ");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) out.print(modifiers + " ");
            out.println(type.getName() + " " + name + ";");
        }
    }
}
