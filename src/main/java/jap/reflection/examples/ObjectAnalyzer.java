package jap.reflection.examples;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class ObjectAnalyzer {
    private final ArrayList<Object> visited = new ArrayList<>();

    /**
     * Konwertuje obiekt na lancuch zawierajacy liste wszystkich pól.
     *
     * @param obj objekt
     * @return łańcuch zawierający nazwę klasy obiektu oraz nazwy i wartości wszystkich pól.
     */
    String toString(Object obj) {
        if (obj == null) return null;
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray()) {
            StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) r.append(",");
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) r.append(val);
                else r.append(toString(val));
            }
            return r + "}";
        }
        StringBuilder r = new StringBuilder(cl.getName());
        do {
            r.append("[");
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.toString().endsWith("[")) r.append(",");
                    r.append(field.getName()).append("=");
                    try {
                        Class<?> t = field.getType();
                        Object val = field.get(obj);
                        if (t.isPrimitive()) r.append(val);
                        else r.append(toString(val));
                    } catch (IllegalAccessException e) {
                        Logger.getLogger(ObjectAnalyzer.class.getName())
                                .log(Level.SEVERE, MessageFormat.format("Illegal access to {0}.", obj.getClass().getName()), e.getMessage());
                    }
                }
            }
            r.append("]");
            cl = cl.getSuperclass();
        }
        while (cl != null);

        return r.toString();
    }
}

