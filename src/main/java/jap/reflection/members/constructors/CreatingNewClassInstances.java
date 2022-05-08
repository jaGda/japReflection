package jap.reflection.members.constructors;

import java.io.Console;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import static java.lang.System.err;
import static java.lang.System.out;

class CreatingNewClassInstances {
    public static void main(String... args) {
        Constructor<?>[] ctors = Console.class.getDeclaredConstructors();
        Constructor<?> ctor = ctors[0];

        try {
            ctor.setAccessible(true);
            // Class.newInstance() vs Constructor.newInstance()
            Console c = (Console) ctor.newInstance();
            Field f = c.getClass().getDeclaredField("cs");
            f.setAccessible(true);
            out.format("Console charset         :  %s%n", f.get(c));
            out.format("Charset.defaultCharset():  %s%n",
                    Charset.defaultCharset());

        } catch (Exception x) {
            err.println(x.getMessage());
        }
    }
}
